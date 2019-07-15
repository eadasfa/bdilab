package com.bdi.lab.test;

import io.fabric8.kubernetes.api.model.Config;
import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.api.model.ReplicationController;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.apache.commons.collections.bag.SynchronizedSortedBag;

import javax.naming.Name;
import javax.naming.ldap.PagedResultsControl;
import java.util.List;

public class TestWithFabric8 {
    private static final String IP = "192.168.0.153";
    private static final String PORT = "8080";
    private static KubernetesClient _kube = new DefaultKubernetesClient("http://"+IP+":"+PORT);
    public static void main(String[] args) {
        Config _config = new Config();

//        REPLICATIONCONTROLLERS
        for (ReplicationController rc : getRC()){
            if(rc.getMetadata().getNamespace().equals("k8s-test"))
                System.out.println(rc.getMetadata().getName());
        }

    }
    public static List<Namespace> getNamespace(){
        return _kube.namespaces().list().getItems();
    }
    public static List<ReplicationController> getRC(){
        return _kube.replicationControllers().list().getItems();
    }
}
