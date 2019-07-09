package com.bdi.lab;

import com.hp.k8s.apiclient.RestfulClient;
import com.hp.k8s.apiclient.imp.JerseyRestfulClient;
import com.hp.k8s.apiclient.imp.Params;
import com.hp.k8s.apiclient.imp.ResourceType;

import com.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Test {
    private static final Logger LOG= LogManager.getLogger(RestfulClient. class .getName());
    private static RestfulClient _restfulClient = new JerseyRestfulClient("http://192.168.0.131:8080/api/v1");
    public static void main(String[] args) {
        Test test = new Test();
//        test.testGetPods();
        test.testCreateNamespce();

    }
    public void testCreateNamespce(){
        Params params =new Params() ;
        params.setResourceType(ResourceType.NAMESPACES) ;
        String jsonName = "namespace.json";
        params.setJson(Utils.getJSON(jsonName));
        LOG.info ("Result :"+ _restfulClient.create(params)) ;
    }

    public void testGetPods(){
        Params params = new Params();
        params.setResourceType(ResourceType.PODS);
        LOG.info("Result: "+ _restfulClient.list(params));
    }
}
