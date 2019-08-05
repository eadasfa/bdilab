package com.bdi.lab.service;

import com.bdi.lab.utils.Common;
import io.fabric8.kubernetes.api.model.ContainerStatus;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientException;
import me.snowdrop.istio.api.networking.v1alpha3.DoneableVirtualService;
import me.snowdrop.istio.api.networking.v1alpha3.VirtualServiceFluent;
import me.snowdrop.istio.api.networking.v1alpha3.VirtualServiceSpecFluent;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bdi.lab.utils.Common._istio;

@Service
public class ServiceServiceImpl implements ServiceService {

    private static final String NAMESPACE = "default";
    private static KubernetesClient _kube = Common._kube;
    public static void main(String[] args) {
//        ServiceServiceImpl s = new ServiceServiceImpl();
//        Map<String,String> map = new HashMap<>();
//        map.put("app","myweb");
//        s.createService(NAMESPACE,map,"NodePort","myweb",8080,30001);
    }
    @Override
    public Map<String, String> getServiceName() {
        Map<String, String> serviceMap = new HashMap<>();
        _kube.services().inNamespace(NAMESPACE).list().getItems().forEach(n->{
            if(!n.getMetadata().getName().equals("kubernetes"))
                serviceMap.put(n.getMetadata().getName(),getState(n.getMetadata().getName()));
        });
        return serviceMap;
    }

    @Override
    public com.bdi.lab.entity.Service getAllInfo(String serviceName) {
        io.fabric8.kubernetes.api.model.Service s = _kube.services()
                .inNamespace(NAMESPACE)
                .withName(serviceName).get();
        com.bdi.lab.entity.Service result = new com.bdi.lab.entity.Service(s);
        result.setState(this.getState(serviceName));
        return result;
    }


    public  String getState(String serviceName) {

        Map<String,String> labels = _kube.services().inNamespace(NAMESPACE).withName(serviceName)
                .get().getSpec().getSelector();
        List<Pod> pods = _kube.pods().inNamespace(NAMESPACE).withLabels(labels).list().getItems();
        boolean flag = false;
        lable:
        for (Pod pod: pods){
            pod.getStatus().getContainerStatuses().get(0).getState();
            List<ContainerStatus> containers = pod.getStatus().getContainerStatuses();
            for(ContainerStatus c:containers){
                if(c.getName().equals(serviceName)&&c.getState().getRunning()!=null){
                    flag=true;
                    break lable;
                }
            }
        }
        if(flag)
            return "Running";
        return "Terminated";

    }
    @Override
    public void updateReplicas(String namespace, String RcName, int num ){
        _kube .replicationControllers()
                .inNamespace(namespace )
                .withName(RcName )
                .edit()
                    .editSpec()
                        .withReplicas(num)
                    .endSpec()
                .done() ;
    }

    @Override
    public Map<String,String> stopService(String serviceName) {
        Map<String,String> resultMap = new HashMap<>();
        if(!getState(serviceName).equals("Running")){
            resultMap.put("message","Error: The service is terminated");
            resultMap.put("code","0");
            return resultMap;
        }
        this.updateReplicas(NAMESPACE,serviceName,0);
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
        if(getState(serviceName).equals("Running")){
            resultMap.put("message","Error: The service is running");
            resultMap.put("code","0");
            return resultMap;
        }
        this.updateReplicas(NAMESPACE ,serviceName ,num);
        resultMap.put("code","1");
        return resultMap;
    }
    @Override
    public void deleteService(String serviceName){
        _kube.services().inNamespace(NAMESPACE).withName(serviceName).delete();
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
            _kube.services().inNamespace(NAMESPACE).create(service);
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
}
