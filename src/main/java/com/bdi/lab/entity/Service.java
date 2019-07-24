package com.bdi.lab.entity;

import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.ServiceSpec;
import io.fabric8.kubernetes.api.model.ServiceStatus;
import java.util.Map;

public class Service{
    private String state;
    private String apiVersion;
    private String kind;
    private ObjectMeta metadata;
    private ServiceSpec spec;
    private ServiceStatus status;
    private Map<String, Object> additionalProperties ;

    public Service(io.fabric8.kubernetes.api.model.Service service){
        setApiVersion(service.getApiVersion());
        setKind(service.getKind());
        setMetadata(service.getMetadata());
        setAdditionalProperties(service.getAdditionalProperties());
        setStatus(service.getStatus());
        setSpec(service.getSpec());
    }
    public String getState() {
        return state;
    }

    public void setState(String phase) {
        this.state = phase;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public ObjectMeta getMetadata() {
        return metadata;
    }

    public void setMetadata(ObjectMeta metadata) {
        this.metadata = metadata;
    }

    public ServiceSpec getSpec() {
        return spec;
    }

    public void setSpec(ServiceSpec spec) {
        this.spec = spec;
    }

    public ServiceStatus getStatus() {
        return status;
    }

    public void setStatus(ServiceStatus status) {
        this.status = status;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

    @Override
    public String toString() {
        return "Service(" +
                "state=" + state  +
                ", apiVersion=" + apiVersion  +
                ", kind=" + kind  +
                ", metadata=" + metadata +
                ", spec=" + spec +
                ", status=" + status +
                ", additionalProperties=" + additionalProperties +
                ')';
    }
}
