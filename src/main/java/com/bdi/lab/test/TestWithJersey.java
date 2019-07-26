package com.bdi.lab.test;
import com.bdi.lab.apiclient.RestfulClient;
import com.bdi.lab.apiclient.imp.JerseyRestfulClient;
import com.bdi.lab.apiclient.imp.Params;
import com.bdi.lab.apiclient.imp.ResourceType;
import com.bdi.lab.utils.Common;
import com.bdi.lab.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;


public class TestWithJersey {
    private static final Logger LOG= LogManager.getLogger(RestfulClient. class .getName());

    // 主节点ip
    private static final String IP = Common.IP;
    private static final int PORT = 8080;
    private static RestfulClient _restfulClient = new JerseyRestfulClient(IP,PORT);
    public static void main(String[] args) {
        TestWithJersey test = new TestWithJersey();
//        test.testCreateNamespace();
//        test.createRC();
        test.createSVC();
    }
    private void createRC(){
        Params params =new Params() ;
        params.setResourceType(ResourceType.REPLICATIONCONTROLLERS) ;
        params.setNamespace("k8s-test");
        String jsonName = "myweb-rc.json";
        params.setJson(Utils.getJSON(jsonName));
        LOG.info ("Result :"+ _restfulClient.create(params)) ;
    }
    private void createSVC(){
        Params params =new Params() ;
        params.setResourceType(ResourceType.SERVICES) ;
        params.setNamespace("k8s-test");
        String jsonName = "myweb-svc.json";
        params.setJson(Utils.getJSON(jsonName));
        LOG.info ("Result :"+ _restfulClient.create(params)) ;
    }
    private void testCreateNamespace(){
        Params params =new Params() ;
        params.setResourceType(ResourceType.NAMESPACES) ;
        String jsonName = "namespace.json";
        params.setJson(Utils.getJSON(jsonName));
        LOG.info ("Result :"+ _restfulClient.create(params)) ;
    }

    private void testGetPods(){
        Params params = new Params();
        params.setResourceType(ResourceType.PODS);
        params.setNamespace("k8s-test");
//        params.setName("pod-sample-in-namespace");
        String result = _restfulClient.list(params);
        LOG.info("Result: "+ result);
        System.out.println(Utils.getPodsFromJson(result));
        params.setName(null);


//        Map<String,String> labels = new HashMap<String,String>();
//        labels.put("k8s-cs","kube-cluster-service");
//        labels.put("k8s-sample-app","kube-service-sample");
//        params.setLabels(labels);
//        result = _restfulClient.list(params);
//        LOG.info("Result: "+ result);
//        params.setLabels(null);
//
//        Map<String,String> notLabels = new HashMap<String,String>();
//        notLabels.put("k8s-cs","kube-cluster-service");
//        params.setNotLabels(notLabels);
//        result = _restfulClient.list(params);
//        LOG.info("Result: "+ result);
//        System.out.println(Utils.getPodsFromJson(result));
//        params.setNotLabels(null);
    }
    private void testCreatePod(){
        Params params = new Params();
        params.setResourceType(ResourceType.PODS);
        params.setJson((Utils.getJSON("podInNs.json")));
        params.setNamespace("ns-sample");
        LOG.info("Result: "+ _restfulClient.create(params));

        params.setJson((Utils.getJSON("pod4ClusterService.json")));
        LOG.info("Result: "+ _restfulClient.create(params));
    }

    // replace方法支支持替换容器 image部分
    // 从API中获取JSON对象，在该JSON对象基础上修改
    private void testReplacePod(){
        Params params = new Params();
        params.setNamespace("ns-sample");
        params.setName("pod-sample-in-namespace");
        params.setJson(Utils.getJSON("pod4Replace.json"));
        params.setResourceType(ResourceType.PODS);
        LOG.info(_restfulClient.replace(params));
    }
    private void testUpdatePod1(){
        Params params = new Params();
        params.setNamespace("ns-sample");
        params.setName("pod-sample-in-namespace");
        params.setJson(Utils.getJSON("pod4MergeJsonPatch.json"));
        params.setResourceType(ResourceType.PODS);
        LOG.info(_restfulClient.updateWithMediaType(params,"application/merge-patch+json"));
    }
    private void testUpdatePod2(){
        Params params = new Params();
        params.setNamespace("ns-sample");
        params.setName("pod-sample-in-namespace");
        params.setJson(Utils.getJSON("pod4StrategicMerge.json"));
        params.setResourceType(ResourceType.PODS);
        LOG.info(_restfulClient.updateWithMediaType(params,"application/strategic-merge-patch+json"));
    }
}
