package com.bdi.lab.utils;

import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import me.snowdrop.istio.client.DefaultIstioClient;
import me.snowdrop.istio.client.IstioClient;

public class Common {
    public static final String IP = "192.168.0.153";
    public static final int PORT = 8080;
    public static final String USERNAME = "root";
    public static final String PASSWORD="123456";
    public static final String NAMESPACE="k8s-test";

    public static Config config = new ConfigBuilder().withMasterUrl("http://"+IP+":"+PORT).build();
    public static KubernetesClient _kube = new DefaultKubernetesClient(config);//使用默认的就足够了
    public static IstioClient _istio = new DefaultIstioClient(config);
//    public static KubernetesClient _kube = new DefaultKubernetesClient("http://"+IP+":"+PORT);
}
