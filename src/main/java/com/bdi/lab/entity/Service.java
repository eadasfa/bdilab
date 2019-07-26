package com.bdi.lab.entity;

import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.ServiceSpec;
import io.fabric8.kubernetes.api.model.ServiceStatus;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
