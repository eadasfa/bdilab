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

    public Map<String,String> stopService(String serviceName);

    public Map<String,String> startService(String Service,Integer num);

    public void deleteService(String serviceName);

    public  Map<String,String> createService(String nameSpace,
                                             Map<String,String> selector,String type,
                                             String name,Integer Port,
                                             Integer nodePort,Map<String,String> lables);

    public int getVersionSize(String serviceName);

    public boolean changeWeight(String serviceName,List<Integer> weights);


}
