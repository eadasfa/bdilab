package com.bdi.lab.test;

import com.bdi.lab.utils.Common;
import io.fabric8.kubernetes.api.model.*;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class TestWithFabric8 {
    private static KubernetesClient _kube = Common._kube;
    public static void main(String[] args) throws InterruptedException {
        ObjectMeta obj =new ObjectMeta();
        obj.setName("myweb");
        obj.setNamespace("k8s-test");
        ServiceSpec spec=new ServiceSpec();
        spec.setType("NodePort");
            ServicePort port=new ServicePort();
            port.setPort(8080);
            port.setNodePort(30001);
            List<ServicePort> servicePorts=new ArrayList<>();
            servicePorts.add(port);
        spec.setPorts(servicePorts);
            Map<String,String> map=new HashMap<>();
            map.put("app","myweb");
        spec.setSelector(map);
       Service service =new Service();
       service.setApiVersion("v1");
       service.setKind("Service");
       service.setMetadata(obj);
       service.setSpec(spec);
       _kube.services().create(service);
    }
    public static int getReplicas(String namespace, String RcName){
        return _kube.replicationControllers().inNamespace(namespace).withName(RcName)
                .get().getSpec().getReplicas().intValue();
    }
    public static void changeReplicas(String namespace, String RcName, int num ){
     _kube .replicationControllers()
             .inNamespace(namespace )
             .withName(RcName )
             .edit()
                .editSpec()
                     .withReplicas(num)
                 .endSpec()
             .done() ;
    }
    //通过RC实现停止服务功能
    public static void stopService(String namespace, String RcName ){
        changeReplicas(namespace ,RcName ,0);
    }
    //通过RC实现启动服务功能
    public static boolean startService(String namespace, String RcName,Integer num){
        if(num<=0){
            System.out.println("Error: num is less than 0！");
            return false;
        }
        changeReplicas(namespace ,RcName ,num);
        return true;
    }
    public static boolean startService(String namespace, String RcName){
        return startService(namespace,RcName,1);
    }
    public static Map<String,String> queryPodPhase(){
        Map<String, String> map = new HashMap<>();
        List<Pod> pl = _kube.pods().inNamespace("k8s-test").withLabel("app","mysql")
                .list()
                .getItems();
        for (Pod po: pl){
            map.put(po.getMetadata().getName(),po.getStatus().getPhase());
        }
        return map;
    }
    public void createService(String nameSpace,String serviceName,
                              Map<String,String> map,String type,String kind,
                              String name,Integer Port,
                              Integer nodePort,
                              String ApiVersion){
        ObjectMeta obj =new ObjectMeta();
        obj.setName(name);
        obj.setNamespace(nameSpace);
        ServiceSpec spec=new ServiceSpec();
        spec.setType(type);
        ServicePort port=new ServicePort();
        port.setPort(Port);
        port.setNodePort(nodePort);
        List<ServicePort> servicePorts=new ArrayList<>();
        servicePorts.add(port);
        spec.setPorts(servicePorts);
        spec.setSelector(map);
        Service service =new Service();
        service.setApiVersion(ApiVersion);
        service.setKind(kind);
        service.setMetadata(obj);
        service.setSpec(spec);
        _kube.services().create(service);

    }
}
