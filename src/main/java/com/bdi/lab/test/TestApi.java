package com.bdi.lab.test;
import com.bdi.lab.apiclient.RestfulClient;
import com.bdi.lab.apiclient.imp.JerseyRestfulClient;
import com.bdi.lab.apiclient.imp.Params;
import com.bdi.lab.apiclient.imp.ResourceType;
import com.bdi.lab.utils.JsonUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class TestApi {
    RestfulClient restfulClient;
    public TestApi(){
       restfulClient=new JerseyRestfulClient("http://192.168.0.153:8080/api/v1");
    }
    // 创建nameespace资源对象
    public void testCreateNameSpace(){
        Params params=new Params();
        params.setResourceType(ResourceType.NAMESPACES);
        params.setJson(JsonUtil.namespace_json);
        System.out.println("result"+restfulClient.create(params));
    }
    // 创建pod资源对象
    public void testCreatePod(){
        Params params=new Params();
        params.setResourceType(ResourceType.NAMESPACES);
        params.setJson(JsonUtil.podInNs_json);
        System.out.println("result"+restfulClient.create(params));
    }
    // 获取pod资源列表
    public void testGetPodList(){
        Params params=new Params();
        params.setResourceType(ResourceType.PODS);
        System.out.println("list:"+restfulClient.list(params));
        Map<String,String> labels=new HashMap<>();
        labels.put("k8s-cs","kube-cluster-service");
        labels.put("k8s-sample-app","kube-service-sample");
        params.setLabels(labels);
        params.setLabels(null);
        Map<String,List<String>> inLabels=new HashMap<>();
        List list=new ArrayList();
        list.add("kube-cluster-service");
        list.add("kube-cluster");
        inLabels.put("k8s-cs",list);
        params.setInLabels(inLabels);
        params.setInLabels(null);
        Map<String,String> fileds=new HashMap<>();
        fileds.put("metadata.name","pod-sample-4-cluster-service");
        params.setNamespace("ns-sample");
        params.setFields(fileds);
    }
}
