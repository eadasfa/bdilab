package com.bdi.lab.test;

import io.fabric8.kubernetes.api.model.ObjectMeta;
import me.snowdrop.istio.api.networking.v1alpha3.DestinationRule;
import me.snowdrop.istio.api.networking.v1alpha3.DestinationRuleSpec;
import me.snowdrop.istio.api.networking.v1alpha3.Subset;
import me.snowdrop.istio.client.DefaultIstioClient;
import me.snowdrop.istio.client.IstioClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bdi.lab.utils.Common.*;

public class TestIstio {
    public static void main(String[] args){
        System.out.println(_istio.destinationRule().inNamespace("k8s-test").withName("myweb").get());
//        createDR();
    }
    public static void createDR(){
        DestinationRule dr = new DestinationRule();
        ObjectMeta om = new ObjectMeta();
        om.setName("myweb");
        dr.setMetadata(om);

        List<Subset> subsetList = new ArrayList<>();
        Subset v1 = new Subset();
        Map<String,String>  v1map = new HashMap<>();
        v1map.put("version","v1");
        v1.setName("v1");
        v1.setLabels(v1map);
        Subset v2 = new Subset();
        Map<String,String>  v2map = new HashMap<>();
        v1map.put("version","v2");
        v2.setName("v2");
        v2.setLabels(v2map);
        subsetList.add(v1);
        subsetList.add(v2);

        DestinationRuleSpec drs = new DestinationRuleSpec();
        drs.setHost("myweb");
        drs.setSubsets(subsetList);
        dr.setSpec(drs);

        _istio.destinationRule().inNamespace("k8s-test").create(dr);
    }
}
