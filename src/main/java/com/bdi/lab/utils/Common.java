package com.bdi.lab.utils;

import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;

public class Common {
    public static final String IP = "192.168.0.153"; //47.93.190.39
    public static final int PORT = 8080;
    public static final String USERNAME = "root";
    public static final String PASSWORD="123456";
    public static KubernetesClient _kube = new DefaultKubernetesClient("http://"+IP+":"+PORT);
}
