package com.bdi.lab.test;

import io.fabric8.kubernetes.api.model.*;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.apache.commons.collections.bag.SynchronizedSortedBag;

import javax.naming.Name;
import javax.naming.ldap.PagedResultsControl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestWithFabric8 {
    private static final String IP = "192.168.0.153";
    private static final String PORT = "8080";
    private static KubernetesClient _kube = new DefaultKubernetesClient("http://"+IP+":"+PORT);
    public static void main(String[] args)  {

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
        List<Pod> pl = _kube.pods().inNamespace("k8s-test").withLabel("app","mysql").list()
                .getItems();
        for (Pod po: pl){
            map.put(po.getMetadata().getName(),po.getStatus().getPhase());
        }
        return map;
    }
}
