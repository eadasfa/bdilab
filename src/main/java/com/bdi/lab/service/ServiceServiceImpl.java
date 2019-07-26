package com.bdi.lab.service;

import com.bdi.lab.utils.Common;
import io.fabric8.kubernetes.api.model.Container;
import io.fabric8.kubernetes.api.model.ContainerStatus;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientException;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ServiceServiceImpl implements ServiceService {
    public static void main(String[] args) {

//    _kube.services().inNamespace("k8s-test").withName("myweb").delete();
        ServiceServiceImpl serImpl=new ServiceServiceImpl();
            Map<String,String> map=new HashMap<>();
            map.put("app","myweb");
        serImpl.creatService(NAMESPACE,map,"NodePort","myweb2",8080,30002);
    }

    private static final String NAMESPACE = "k8s-test";
    private static KubernetesClient _kube = Common._kube;
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
            return "Runing";
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
    public void stopService(String serviceName) {
        this.updateReplicas(NAMESPACE,serviceName,0);
    }

    @Override
    public boolean startService( String serviceName,Integer num){
        if(num<=0){
            System.out.println("Error: num is less than 0！");
            return false;
        }
        this.updateReplicas(NAMESPACE ,serviceName ,num);
        return true;
    }
    public void deleteService(String serviceName){
        _kube.services().inNamespace(NAMESPACE).withName(serviceName).delete();
    }
    public void creatService(String nameSpace,
                             Map<String,String> map,String type,
                             String name,Integer Port,
                             Integer nodePort){
        com.bdi.lab.entity.Service service=com.bdi.lab.entity.Service.newInstance(nameSpace,map,type,name,Port,nodePort);
        try {
            _kube.services().inNamespace(NAMESPACE).create(service);
        }catch (KubernetesClientException e){
            System.out.println(e.getMessage());
        }
    }
}
