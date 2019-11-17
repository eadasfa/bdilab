package com.bdi.lab.utils;

import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import me.snowdrop.istio.client.DefaultIstioClient;
import me.snowdrop.istio.client.IstioClient;
public class Common {
    public static  String IP = "192.168.0.0";
    public static final int PORT = 8080;
    public static  String USERNAME = "rodot";
    public static  String PASSWORD="12345df6";
    public static final String NAMESPACE="k8s-test";
    public static Config config = new ConfigBuilder().withMasterUrl("http://"+IP+":"+PORT).build();
    public static KubernetesClient _kube = new DefaultKubernetesClient(config);//使用默认的就足够了
    public static IstioClient _istio = new DefaultIstioClient(config);
//    public static KubernetesClient _kube = new DefaultKubernetesClient("http://"+IP+":"+PORT);
    public static void resetKubeAndIstioClient(){
        config = new ConfigBuilder().withMasterUrl("http://"+IP+":"+PORT).build();
         _kube = new DefaultKubernetesClient(config);//使用默认的就足够了
        _istio = new DefaultIstioClient(config);
    }


}
