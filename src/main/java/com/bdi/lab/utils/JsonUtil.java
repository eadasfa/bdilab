package com.bdi.lab.utils;

public class JsonUtil {
   public static String namespace_json = "{\"kind\":\"Namespace\",\"apiVersion\":\"v1\",\"metadata\":{\"name\":\"ns-sample\"}}";
   public static String podInNs_json = "{\"kind\":\"Pod\",\"apiVersion\":\"v1\"," +
           "\"metadata\":{\"name\":\"ns-sample\",\"namespace\":\"ns-sample\"}," +
           "\"spec\":{\"containers\":[{\"name\":\"mycontainer\",\"image\":\"kubeguide/redis-master\"}]}}";

}
