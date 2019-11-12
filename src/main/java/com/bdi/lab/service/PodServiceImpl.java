package com.bdi.lab.service;

import com.bdi.lab.utils.Common;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodBuilder;
import org.springframework.stereotype.Service;

@Service
public class PodServiceImpl implements PodService {

    @Override
    public Pod createPod(String nsName, String podName, String labelkey, String labelvalue, String containerName, String imageName, int cnPort) {
        Pod pod = new PodBuilder()
                .withApiVersion("v1")
                .withKind("Pod")
                .withNewMetadata()
                .withName(podName)
                .withNamespace(nsName)
                .addToLabels(labelkey, labelvalue)
                .endMetadata()
                .withNewSpec()
                .addNewContainer()
                .withName(containerName)
                .withImage(imageName)
                .addNewPort()
                .withContainerPort(cnPort)
                .endPort()
                .endContainer()
                .endSpec()
                .build();
        try {
            //Pod 创建
            Common._kube.pods().create(pod);
            System.out.println("pod create success");
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("pod create failed");
        }
        return pod;
    }

    @Override
    public Pod deletePod(String namespaceName, String podName) {
        Pod pod = new Pod();
        try {
            //获取要删除的pod
            pod = Common._kube.pods().inNamespace(namespaceName).withName(podName).get();
            //Pod 删除
            Common._kube.pods().inNamespace(namespaceName).withName(podName).delete();
            System.out.println("pod delete success");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("pod create failed");
        }
        return pod;
    }

    @Override
    public Pod readPod(String namespaceName, String podName) {
        Pod pod = new Pod();
        try {
            //获取要查询的pod
            pod = Common._kube.pods().inNamespace(namespaceName).withName(podName).get();
            System.out.println("pod read success");
        }catch (Exception e){
            System.out.println("pod read failed");
            e.printStackTrace();
        }
        return pod;
    }
}
