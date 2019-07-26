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
//        changeReplicas("default","mysql",3);
//        System.out.println(_kube.pods().inNamespace("k8s-test").list().getItems().get(0).getStatus().getContainerStatuses().get(0));
//       changeReplicas("k8s-test","mysql",3);
//        Container container = _kube.pods().inNamespace("default").withLabel("app","mysql")
//                .list().getItems().get(0).getSpec().getContainers().get(0);
//        _kube.namespaces().list().getItems().forEach(n->{
//            System.out.println(n.getMetadata().getName());
//        });

        deleteService("k8s-test","myweb2");
    }
    public static void deleteService(String namespace,String serviceName){
        _kube.services().inNamespace(namespace).withName(serviceName).delete();
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
}
