package com.bdi.lab.test;

import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import net.sf.json.JSONObject;

public class TestJson {
    public static void main(String[] args) {
        Namespace ns = new Namespace();
//        ns.setApiVersion("v1");
//        ns.setKind("Namespace");
        ObjectMeta om = new ObjectMeta();
        om.setName("ns-sample");
        ns.setMetadata(om);

        JSONObject o = JSONObject.fromObject(ns);
        System.out.println(o.toString());
    }
}
