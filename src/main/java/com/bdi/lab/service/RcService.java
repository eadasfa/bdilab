package com.bdi.lab.service;

import io.fabric8.kubernetes.api.model.ReplicationController;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import org.springframework.stereotype.Service;

@Service
public interface RcService {

    //创建Replication Controller
    ReplicationController createRC(String rcName,
                                   String nsName,
                                   String lbkey,
                                   String lbvalue,
                                   int replicas,
                                   String ctName,
                                   String imName,
                                   int cnPort);
    //创建Replication Controller
    Deployment createDeployment(String rcName,
                                String nsName,
                                String lbkey,
                                String lbvalue,
                                int replicas,
                                String ctName,
                                String imName,
                                int cnPort);
    //删除Replication Controller
    ReplicationController deleteRC(String nsName, String rcName);
    //查询Replication Controller
    ReplicationController readRC(String nsName, String rcName);
    Deployment readDP(String nsName, String rcName) ;

}
