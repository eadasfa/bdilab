package com.bdi.lab.entity;

import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.ServicePort;
import io.fabric8.kubernetes.api.model.ServiceSpec;
import io.fabric8.kubernetes.api.model.ServiceStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Service extends io.fabric8.kubernetes.api.model.Service{
    private String state;

    public Service(io.fabric8.kubernetes.api.model.Service service){
        setApiVersion(service.getApiVersion());
        setKind(service.getKind());
        setMetadata(service.getMetadata());
        setStatus(service.getStatus());
        setSpec(service.getSpec());
        service.getAdditionalProperties().forEach((k,v)->{
            setAdditionalProperty(k,v);
        });
    }
    public Service(){

    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    public static Service newInstance(String nameSpace,String serviceName,
                              Map<String,String> map,String type,
                              String name,Integer Port,
                              Integer nodePort){
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
        service.setMetadata(obj);
        service.setSpec(spec);
        return service;

    }
}
