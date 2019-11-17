package com.bdi.lab.service;
import com.bdi.lab.test.TestIstio;
import com.bdi.lab.utils.Common;
import io.fabric8.kubernetes.api.model.ContainerStatus;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.ServiceBuilder;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientException;
import me.snowdrop.istio.api.networking.v1alpha3.DoneableVirtualService;
import me.snowdrop.istio.api.networking.v1alpha3.VirtualServiceFluent;
import me.snowdrop.istio.api.networking.v1alpha3.VirtualServiceSpecFluent;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.bdi.lab.utils.Common._istio;
@Service
public class ServiceServiceImpl implements ServiceService {

    private static final String NAMESPACE = "default";
//    private static KubernetesClient _kube = Common._kube;
    public static void main(String[] args) {
        ServiceServiceImpl s = new ServiceServiceImpl();
//        Map<String,String> map = new HashMap<>();
//        map.put("app","myweb");
//        s.createService(NAMESPACE,map,"NodePort","myweb",8080,30001);
//        s.stopService("equipment");
        s.deleteService("arms");
    }
    @Override
    public List<Map<String, Object>> getServiceName() {
        List<Map<String,Object>> serviceList = new ArrayList<>();
        Common._kube.services().inNamespace(NAMESPACE).list().getItems().forEach(n->{
            if(!n.getMetadata().getName().equals("kubernetes")) {
                Map<String, Object> map = new HashMap<>();
                map.put("name",n.getMetadata().getName());
                // 版本数量
                try {
                    map.put("version",getVersionSize(n.getMetadata().getName()) );
                }catch (Exception e){
                    map.put("version",1);
                }
                map.put("state",getState(n.getMetadata().getName()));
                serviceList.add(map);
            }
        });
        return serviceList;
    }

    @Override
    public com.bdi.lab.entity.Service getAllInfo(String serviceName) {
        io.fabric8.kubernetes.api.model.Service s = Common._kube.services()
                .inNamespace(NAMESPACE)
                .withName(serviceName).get();
        com.bdi.lab.entity.Service result = new com.bdi.lab.entity.Service(s);
        result.setState(this.getState(serviceName));
        return result;
    }


    public  String getState(String serviceName) {

        Map<String,String> labels = Common._kube.services().inNamespace(NAMESPACE).withName(serviceName)
                .get().getSpec().getSelector();
        List<Pod> pods = Common._kube.pods().inNamespace(NAMESPACE).withLabels(labels).list().getItems();
        boolean flag = false;
        lable:
        for (Pod pod: pods){
            pod.getStatus().getContainerStatuses().get(0).getState();
            List<ContainerStatus> containers = pod.getStatus().getContainerStatuses();
            for(ContainerStatus c:containers){
                if(c.getState().getRunning()!=null){
                    flag=true;
                    break lable;
                }
            }
        }
        if(flag)
            return "运行中";
        return "停止";
    }

    @Override
    public void updateReplicas(String namespace, String serviceName, int num ){

        Common._kube .apps().deployments()
                .inNamespace(namespace )
                .withName(serviceName )
                .edit()
                .editSpec()
                .withReplicas(num)
                .endSpec()
                .done() ;
//        Common._kube .replicationControllers()
//                .inNamespace(namespace )
//                .withName(serviceName )
//                .edit()
//                    .editSpec()
//                        .withReplicas(num)
//                    .endSpec()
//                .done() ;
    }

    @Override
    public Map<String,String> stopService(String serviceName) {
        Map<String,String> resultMap = new HashMap<>();
        if(!getState(serviceName).equals("运行中")){
            resultMap.put("message","Error: The service is terminated");
            resultMap.put("code","0");
            return resultMap;
        }
        List<Deployment> deployments= Common._kube.apps().deployments().inNamespace(NAMESPACE)
                .withLabel("app",serviceName).list().getItems();
        for(Deployment dp : deployments){
            this.updateReplicas(NAMESPACE,dp.getMetadata().getName(),0);
        }
        resultMap.put("code","1");
        return resultMap;
    }

    @Override
    public Map<String,String> startService( String serviceName,Integer num){
        Map<String,String> resultMap = new HashMap<>();
        if(num<=0){
            resultMap.put("message","Error: num is less than 0！");
            resultMap.put("code","0");
            return resultMap;
        }
        if(getState(serviceName).equals("运行中")){
            resultMap.put("message","Error: The service is running");
            resultMap.put("code","0");
            return resultMap;
        }
        List<Deployment> deployments= Common._kube.apps().deployments().inNamespace(NAMESPACE)
                .withLabel("app",serviceName).list().getItems();
        for(Deployment dp : deployments){
            this.updateReplicas(NAMESPACE,dp.getMetadata().getName(),num);
        }
        resultMap.put("code","1");
        return resultMap;
    }
    @Override
    public void deleteService(String serviceName){
        Common._kube.services().inNamespace(NAMESPACE).withName(serviceName).delete();
    }

    @Override
    public Map<String,String> createService(String nameSpace,
                                            Map<String,String> selector,String type,
                                            String name,Integer Port,
                                            Integer nodePort,Map<String,String> lables){
        Map<String,String> resultMap = new HashMap<>();
        com.bdi.lab.entity.Service service=com.bdi.lab.entity.Service.newInstance(nameSpace,
                selector,type,name,Port,nodePort,lables);
        try {
            Common._kube.services().inNamespace(NAMESPACE).create(service);
        }catch (KubernetesClientException e){
//            System.out.println(e.getMessage());
            resultMap.put("message",e.getMessage());
            resultMap.put("code","0");
            return resultMap;
        }
        resultMap.put("code","1");
        return resultMap;
    }

    @Override
    public int getVersionSize(String serviceName) {
        return _istio.virtualService().inNamespace(NAMESPACE).withName(serviceName)
                .get().getSpec().getHttp().get(0).getRoute().size();

    }

    @Override
    public boolean changeWeight(String serviceName, List<Integer> weights) {
        VirtualServiceSpecFluent.HttpNested<VirtualServiceFluent.SpecNested<DoneableVirtualService>>
                http = _istio.virtualService().inNamespace(NAMESPACE).withName(serviceName)
                .edit()
                .editSpec()
                .editFirstHttp();
        int arraySum = 0;
        for(Integer n :weights) arraySum += n;
        int routeSize = http.getRoute().size();
        if(arraySum<=0) return false;
        int tempSum = 0;
        for(int i=0;i<routeSize;i++){
            int weight= (int)((1.0*weights.get(i)/arraySum) * 100);
            if(i==routeSize-1) weight = 100-tempSum;
            http = http.editRoute(i)
                    .withWeight(weight)
                    .endRoute();
            tempSum += weight;
        }
        http.endHttp().endSpec().done();
        return true;
    }
    /**
     * 设置服务的优先级:其中name为副本控制器的名称
     * */
    @Override
    public String change_priority(String name, String priorityName) {
        String destinatioruleName = name.split("-")[0];
        if(priorityName.equals("high"))
            TestIstio.setMaxConnection("default",destinatioruleName,10,10,10,10);
        if(priorityName.equals("low"))
            TestIstio.setMaxConnection("default",destinatioruleName,5,5,5,5);
        if(priorityName.equals("middle"))
            TestIstio.setMaxConnection("default",destinatioruleName,8,8,8,8);
        Common._kube.apps().deployments()
                .inNamespace("default")
                .withName(name)
                .edit()
                .editSpec()
                .editTemplate()
                .editSpec()
                .withNewPriorityClassName(priorityName)
                .endSpec()
                .endTemplate()
                .endSpec()
                .done();
        return "设置成功!";
    }

    /**
     * 新的服务创建
     * */
    public static io.fabric8.kubernetes.api.model.Service createService(String seriveName, String nsName, String labelkey, String labelvalue, int cnPort,int ndport){
        io.fabric8.kubernetes.api.model.Service service = new ServiceBuilder()
                .withApiVersion("v1")
                .withKind("Service")
                .withNewMetadata()
                .withName(seriveName)
                .withNamespace(nsName)
                .addToLabels(labelkey, labelvalue)
                .endMetadata()
                .withNewSpec()
                .addNewPort()
//                .withNodePort(ndport)
                .withPort(cnPort)
                .endPort()
                //.withType("NodePort")
                .addToSelector(labelkey,labelvalue)
                .endSpec()
                .build();
        try {
            Common._kube.services().create(service);
            System.out.println("service create success");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("service create failed");
        }
        return service;
    }


}
