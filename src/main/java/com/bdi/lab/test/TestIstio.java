package com.bdi.lab.test;

import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.ReplicationControllerFluent;
import me.snowdrop.istio.api.networking.v1alpha3.*;
import me.snowdrop.istio.client.DefaultIstioClient;
import me.snowdrop.istio.client.IstioClient;

import java.util.*;

import static com.bdi.lab.utils.Common.*;

public class TestIstio {
    public static String namespace = "default";
    public static void main(String[] args){
//        System.out.println(_istio.destinationRule().inNamespace("k8s-test").withName("helloworld").get());
//        _kube.services().inNamespace("k8s-test").withName("helloworld").delete();
//        System.out.println(_istio.virtualService().inNamespace("k8s-test").withName("helloworld").get());
//        _istio.destinationRule().inNamespace(namespace).withName("helloworld").delete();
//        _istio.virtualService().inNamespace(namespace).withName("helloworld").delete();
//        createDR();
//        _istio.virtualService().inNamespace("default").create(newInstance());
//        showWeight();
//
        changeWeight(namespace,"reviews", new Integer[]{1,4,3});

    }
    public static boolean changeWeight(String namespace, String name,Integer[] weights){
        VirtualServiceSpecFluent.HttpNested<VirtualServiceFluent.SpecNested<DoneableVirtualService>>
                http = _istio.virtualService().inNamespace(namespace).withName(name)
                .edit()
                .editSpec()
                .editFirstHttp();
        int routeSize = http.getRoute().size();
        int arraySum = 0;
        for(Integer n :weights) arraySum += n;
        if(arraySum<=0) return false;
        int tempSum = 0;
        for(int i=0;i<routeSize;i++){
            int weight= (int)((1.0*weights[i]/arraySum) * 100);
            if(i==routeSize-1) weight = 100-tempSum;
            http = http.editRoute(i)
                    .withWeight(weight)
                    .endRoute();
            System.out.println(weight);
            tempSum += weight;
        }
        http.endHttp().endSpec().done();
        return true;
    }
    public static void showWeight(){
        _istio.virtualService().inNamespace(namespace).list().getItems().get(0).getSpec().getHttp()
                .get(0).getRoute().forEach(n->{
            System.out.println(n.getWeight());
        });
    }
    public static void createDR(){
        DestinationRule dr = new DestinationRule();
        ObjectMeta om = new ObjectMeta();
        om.setName("helloworld");
        dr.setMetadata(om);

        List<Subset> subsetList = new ArrayList<>();
        Subset v1 = new Subset();
        Map<String,String>  v1map = new HashMap<>();
        v1map.put("version","v1");
        v1.setName("v1");
        v1.setLabels(v1map);
        Subset v2 = new Subset();
        Map<String,String>  v2map = new HashMap<>();
        v2map.put("version","v2");
        v2.setName("v2");
        v2.setLabels(v2map);
        subsetList.add(v1);
        subsetList.add(v2);

        DestinationRuleSpec drs = new DestinationRuleSpec();
        drs.setHost("helloworld");
        drs.setSubsets(subsetList);
        dr.setSpec(drs);

        _istio.destinationRule().inNamespace(namespace).create(dr);
    }
    public static VirtualService newInstance(){
        VirtualService virtualService=new VirtualService();
        ObjectMeta mata=new ObjectMeta();
        mata.setName("helloworld");
        virtualService.setMetadata(mata);
        VirtualServiceSpec spec=new VirtualServiceSpec();
        List<String> hostList=new ArrayList<String>();
        hostList.add("helloworld");
        spec.setHosts(hostList);
        List<HTTPRoute> httpRouteList=new ArrayList<HTTPRoute>();
        List<HTTPRouteDestination> httpRouteDestinationList=new ArrayList<HTTPRouteDestination>();
        Destination destination=new Destination();
        destination.setHost("helloworld");
        destination.setSubset("v1");
        Destination destination2=new Destination();
        destination2.setHost("helloworld");
        destination2.setSubset("v2");
        httpRouteDestinationList.add(new HTTPRouteDestination());
        httpRouteDestinationList.add(new HTTPRouteDestination());
        httpRouteDestinationList.get(0).setDestination(destination);
        httpRouteDestinationList.get(1).setDestination(destination2);
        httpRouteList.add(new HTTPRoute());
        httpRouteList.get(0).setRoute(httpRouteDestinationList);
        httpRouteDestinationList.get(0).setWeight(75);
        httpRouteDestinationList.get(1).setWeight(25);
        spec.setHttp(httpRouteList);

        virtualService.setSpec(spec);

        return virtualService;
    }
}
