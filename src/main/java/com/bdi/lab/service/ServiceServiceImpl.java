package com.bdi.lab.service;

import com.bdi.lab.utils.Common;
import io.fabric8.kubernetes.api.model.Container;
import io.fabric8.kubernetes.api.model.ContainerStatus;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ServiceServiceImpl implements ServiceService {

    private static final String NAMESPACE = "default";
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

    public static void main(String[] args) {
        ServiceServiceImpl ss = new ServiceServiceImpl();
////        ss.getAllInfo("mysql");
//        System.out.println(ss.getStatus("mysql"));
        System.out.println(JSONObject.fromObject(ss.getServiceName()));
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

    @Override
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

}
