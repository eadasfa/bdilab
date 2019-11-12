package com.bdi.lab.service;

import io.fabric8.kubernetes.api.model.Pod;
import org.springframework.stereotype.Service;


public interface PodService {
    // Pod的创建
   Pod createPod(String nsName,
                String podName,
                String labelkey,
                String labelvalue,
                String containerName,
                String imageName,
                int cnPort);
    // 删除Pod
    Pod deletePod(String namespaceName, String podName);
    // Pod的查询
    Pod readPod(String namespaceName, String podName);
}
