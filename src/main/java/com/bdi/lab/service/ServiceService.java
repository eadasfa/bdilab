package com.bdi.lab.service;

import java.util.List;
import java.util.Map;

public interface ServiceService {
    // 得到服务名字
    public Map<String,String> getServiceName();
    // 得到一个服务的所有信息
    public com.bdi.lab.entity.Service getAllInfo(String serviceName);

    public String getState(String serviceName);

    public void updateReplicas(String namespace, String RcName, int num );

    public boolean stopService(String serviceName);

    public boolean startService(String Service,Integer num);
}
