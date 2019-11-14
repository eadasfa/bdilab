package com.bdi.lab.service;

import com.bdi.lab.utils.Common;
import io.fabric8.kubernetes.api.model.ReplicationController;
import io.fabric8.kubernetes.api.model.ReplicationControllerBuilder;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import io.fabric8.kubernetes.api.model.apps.DeploymentBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RcServiceImpl implements RcService {

    @Override
    public ReplicationController createRC(String rcName, String nsName, String lbkey, String lbvalue, int replicas, String ctName, String imName, int cnPort) {
        ReplicationController rc = new ReplicationControllerBuilder()
                .withApiVersion("v1")
                .withKind("ReplicationController")
                .withNewMetadata()
                .withName(rcName)
                .withNamespace(nsName)
                .addToLabels(lbkey, lbvalue)
                .endMetadata()
                .withNewSpec()
                .withReplicas(replicas)
                .addToSelector(lbkey, lbvalue)
                .withNewTemplate()
                .withNewMetadata()
                .addToLabels(lbkey, lbvalue)
                .endMetadata()
                .withNewSpec()
                .addNewContainer()
                .withName(ctName)
                .withImage(imName)
                .addNewPort()
                .withContainerPort(cnPort)
                .endPort()
                .endContainer()
                .endSpec()
                .endTemplate()
                .endSpec()
                .build();
        try {
            Common._kube.replicationControllers().create(rc);
            System.out.println("replication controller create success");
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("replication controller create failed");
        }
        return rc;
    }
    @Override
    public Deployment createDeployment(String rcName, String nsName, String lbkey, String lbvalue, int replicas, String ctName, String imName, int cnPort){
        Deployment deployment = new DeploymentBuilder()
                .withNewMetadata()
                .withName(rcName)
                .withNamespace(nsName)
                .addToLabels(lbkey, lbvalue)
                .endMetadata()
                .withNewSpec()
                .withReplicas(replicas)
                .withNewSelector()
                //
                .addToMatchLabels(lbkey,lbvalue)
                .endSelector()
                .withNewTemplate()
                .withNewMetadata()
                //
                .addToLabels(lbkey, lbvalue)
                .endMetadata()
                .withNewSpec()
                .addNewContainer()
                .withName(ctName)
                .withImage(imName)
                .withImagePullPolicy("IfNotPresent")
                .addNewPort()
                .withContainerPort(cnPort)
                .endPort()
                .endContainer()
                .endSpec()
                .endTemplate()
                .endSpec()
                .build();
        try {
            Common._kube.apps().deployments().create(deployment);
            System.out.println("replication controller create success");
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("replication controller create failed");
        }
        return deployment;
    }

    @Override
    public ReplicationController deleteRC(String nsName, String rcName) {
        ReplicationController rc = new ReplicationController();
        try {
            rc = Common._kube.replicationControllers().inNamespace(nsName).withName(rcName).get();
            Common._kube.replicationControllers().inNamespace(nsName).withName(rcName).delete();
            System.out.println("replication controller delete success");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("replication controller delete failed");
        }
        return rc;
    }

    @Override
    public ReplicationController readRC(String nsName, String rcName) {
        ReplicationController rc = new ReplicationController();
        try {
            rc = Common._kube.replicationControllers().inNamespace(nsName).withName(rcName).get();
            System.out.println("replication controller read success");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("replication controller read failed");
        }
        return rc;
    }
    @Override
    public Deployment readDP(String nsName, String rcName) {
        Deployment rc = new Deployment();
        try {
            rc = Common._kube.apps().deployments().inNamespace(nsName).withName(rcName).get();
            System.out.println("replication controller read success");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("replication controller read failed");
        }
        return rc;
    }
    /**
     * 获取所有的副本信息
    * */
    @Override
    public List<Map<Object,Object>> getAllDeployment(String nsName) {
        List<Deployment> list= Common._kube.apps().deployments().inNamespace(nsName).list().getItems();
        List<Map<Object,Object>> result=new ArrayList<>();
        for(Deployment d:list){
            Map<Object,Object> map=new HashMap<>();
            map.put("name",d.getMetadata().getName());
            // 副本数量
            map.put("replicas",d.getSpec().getReplicas());
            map.put("creationTimestamp",d.getMetadata().getCreationTimestamp());
            result.add(map);
        }
        return result;
    }
}
