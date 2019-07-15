package com.bdi.lab.test;

import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.api.model.ReplicationController;
import io.fabric8.kubernetes.api.model.ReplicationControllerSpec;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.apache.commons.collections.bag.SynchronizedSortedBag;

import javax.naming.Name;
import javax.naming.ldap.PagedResultsControl;
import java.util.List;

public class TestWithFabric8 {
    private static final String IP = "192.168.0.153";
    private static final String PORT = "8080";
    private static Config _config = new Config();
//    private static Config config = new ConfigBuilder().withMasterUrl("http://"+IP+":"+PORT).build();
//    private static KubernetesClient _kube = new DefaultKubernetesClient(config);
    private static KubernetesClient _kube = new DefaultKubernetesClient("http://"+IP+":"+PORT);
    public static void main(String[] args) {
//        REPLICATIONCONTROLLERS
        ReplicationController rc = null;
        for (ReplicationController item : getRC()){
            if(item.getMetadata().getName().equals("mysql"))
                rc = item;
        }
        rc.getSpec().setReplicas(2);
        rc.getMetadata().setNamespace("k8s-test");
//        System.out.println(rc.toString());
//        System.out.println(_kube.replicationControllers());

    }
    public static List<Namespace> getNamespace(){
        return _kube.namespaces().list().getItems();
    }
    public static List<ReplicationController> getRC(){
        return _kube.replicationControllers().inNamespace("k8s-test").list().getItems();
    }

}
