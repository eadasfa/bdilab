package com.bdi.lab.test;

import com.bdi.lab.service.ServiceServiceImpl;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.ReplicationControllerFluent;
import me.snowdrop.istio.api.networking.v1alpha3.*;
import me.snowdrop.istio.client.DefaultIstioClient;
import me.snowdrop.istio.client.IstioClient;

import java.util.*;

import static com.bdi.lab.utils.Common.*;

public class TestIstio {
    public static String namespace = "default";
    public static void main(String[] args){

//        _istio.destinationRule().inNamespace(namespace).withName("productpage")
//                .edit()
//                .editSpec()
//                .editOrNewTrafficPolicy()
//                .editOrNewConnectionPool()
//                .editOrNewTcp()
//                .withMaxConnections(1)
//                .endTcp()
//                .endConnectionPool()
//                .endTrafficPolicy()
//                .endSpec()
//                .done();

        _istio.destinationRule().inNamespace(namespace).withName("productpage")
                .edit()
                .editSpec()
                .editOrNewTrafficPolicy()
                .editOrNewOutlierDetection()
                .editOrNewBaseEjectionTime()
                .withSeconds(180L)
                .and()
                .endOutlierDetection()
                .endTrafficPolicy()
                .endSpec()
                .done();
    }
    public static void showWeight(){
        _istio.virtualService().inNamespace(namespace).list().getItems().get(1).getSpec().getHttp()
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
