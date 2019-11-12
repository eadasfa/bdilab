package com.bdi.lab.service;

import com.bdi.lab.utils.Common;
import io.fabric8.kubernetes.api.model.ReplicationController;
import io.fabric8.kubernetes.api.model.ReplicationControllerBuilder;
import org.springframework.stereotype.Service;

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
}
