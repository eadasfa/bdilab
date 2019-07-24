package com.bdi.lab.service;

import io.fabric8.kubernetes.api.model.Container;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ServiceServiceImpl implements ServiceService {
    private static final String IP = "192.168.0.153";
    private static final String PORT = "8080";
    private static final String NAMESPACE = "default";
    private static KubernetesClient _kube = new DefaultKubernetesClient("http://"+IP+":"+PORT);
    @Override
    public Map<String, String> getServiceName() {
        Map<String, String> serviceMap = new HashMap<>();
        _kube.services().inNamespace(NAMESPACE).list().getItems().forEach(n->{
            serviceMap.put(n.getMetadata().getName(),getStatus(n.getMetadata().getName()));
        });
        return serviceMap;
    }

    public static void main(String[] args) {
        ServiceServiceImpl ss = new ServiceServiceImpl();
//        ss.getAllInfo("mysql");
        System.out.println(ss.getStatus("mysql"));
    }
    @Override
    public com.bdi.lab.entity.Service getAllInfo(String serviceName) {
        io.fabric8.kubernetes.api.model.Service s = _kube.services()
                .inNamespace("k8s-test")
                .withName(serviceName).get();
        com.bdi.lab.entity.Service result = new com.bdi.lab.entity.Service(s);
        result.setState("Running");
        System.out.println(s);
        System.out.println(result);
        return result;
    }

    @Override
    public String getStatus(String serviceName) {

        Map<String,String> labels = _kube.services().inNamespace(NAMESPACE).withName(serviceName)
                .get().getSpec().getSelector();
        List<Pod> pods = _kube.pods().inNamespace(NAMESPACE).withLabels(labels).list().getItems();
        boolean flag = false;
//        for (Pod pod: pods){
//            for (Container container:pod.getSpec().getContainers()){
//                if(container.getName().equals(serviceName) && container.){
//
//                }
//            }
//        }
//        _kube.services().inNamespace(NAMESPACE).withName("mysql")
//                .edit()
//                .editStatus()
//                .
        pods.get(0).getStatus().getContainerStatuses().forEach(n->{
            System.out.println(n.getName()+"\n"+n.getState().getRunning());
        });
        return "";
    }

}
