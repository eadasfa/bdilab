package com.bdi.lab;

import com.bdi.lab.apiclient.RestfulClient;
import com.bdi.lab.apiclient.imp.JerseyRestfulClient;
import com.bdi.lab.apiclient.imp.Params;
import com.bdi.lab.apiclient.imp.ResourceType;
import com.bdi.lab.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class test1 {
    private static RestfulClient _restfulClient=new JerseyRestfulClient("http://192.168.0.131:8080/api/v1");
    private static final Logger LOG= LogManager.getLogger(RestfulClient. class .getName());
    private static void testGetPods(){
        Params params=new Params();
        params.setResourceType(ResourceType.PODS  );

        LOG.info("result"+_restfulClient .list(params) );


    }
    private static void testCreateNamespeace(){
        Params params=new Params();
        params.setResourceType(ResourceType.NAMESPACES );
        String namespaceJson= "namespace.json";
        params.setJson(Utils.getJSON(namespaceJson));
        LOG.info("Result: "+_restfulClient .create(params));
    }
  /*  public static void testCreatpods(){
        Params params=new Params();
        params.setResourceType(ResourceType.PODS);

    }*/
    public static void main(String[] args) {
        testCreateNamespeace();
    }
}
