package com.bdi.lab.test;

import com.bdi.lab.utils.Common;
import io.fabric8.kubernetes.api.model.*;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import me.snowdrop.istio.api.networking.v1alpha3.VirtualService;
import me.snowdrop.istio.client.DefaultIstioClient;
import me.snowdrop.istio.client.IstioClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class TestWithFabric8 {
//    private static KubernetesClient _kube = Common._kube;
    public static void main(String[] args) throws InterruptedException {
//        _kube.apps().replicaSets().inNamespace("default").withName("ratings-v1-858fb7569b").edit().editSpec().editTemplate().editSpec().withNewPriorityClassName("high-priority")
//        .endSpec()
//                .endTemplate()
//                .endSpec().done();
//        _kube.apps().deployments().inNamespace("default").withName("reviews-v2").edit().editSpec().editTemplate().editSpec()
////                .withNewPriorityClassName("high-priority")
////                .endSpec()
////                .endTemplate()
////                .endSpec()
////                .done();
       // change_priority("default","reviews-v2","medium-priority");

    }
    public static void changeLabelsInRc(String namespace,String name,Map<String,String> labels){
        Common._kube.replicationControllers().inNamespace(namespace).withName(name)
                .edit()
                .editSpec()
                .editTemplate()
                .editMetadata()
                .addToLabels(labels)
                .endMetadata()
                .endTemplate()
                .endSpec()
                .done();
    }
    public static int getReplicas(String namespace, String RcName){
        return Common._kube.replicationControllers().inNamespace(namespace).withName(RcName)
                .get().getSpec().getReplicas().intValue();
    }
    public static void changeReplicas(String namespace, String RcName, int num ){
        Common._kube .replicationControllers()
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
        List<Pod> pl = Common._kube.pods().inNamespace("k8s-test").withLabel("app","mysql")
                .list()
                .getItems();
        for (Pod po: pl){
            map.put(po.getMetadata().getName(),po.getStatus().getPhase());
        }
        return map;
    }

}
