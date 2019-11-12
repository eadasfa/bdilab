package com.bdi.lab.controller;

import com.bdi.lab.service.PodService;
import com.bdi.lab.utils.Common;
import io.fabric8.kubernetes.api.model.NamespaceList;
import io.fabric8.kubernetes.api.model.NodeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/pod")
public class PodController {
    @Autowired
    PodService podService;

    /**
     * Pod的创建
    * */
    @RequestMapping(value = "/createpod", method = RequestMethod.POST)
    public ResponseEntity createk8spod(@RequestParam(value = "nsName") String nsName,
                                       @RequestParam(value = "podName") String pdName,
                                       @RequestParam(value = "labelkey") String labelkey,
                                       @RequestParam(value = "labelvalue") String labelvalue,
                                       @RequestParam(value = "containerName") String containerName,
                                       @RequestParam(value = "imageName") String imageName,
                                       @RequestParam(value = "containerPort") int containerPort){
        return ResponseEntity.ok(podService.createPod(nsName,pdName,labelkey,labelvalue, containerName, imageName,containerPort));
    }

    /**
     * pod的删除
    * */
    @RequestMapping(value = "/deletepod", method = RequestMethod.DELETE)
    public ResponseEntity deletek8spod(@RequestParam(value = "nsName") String nsName,
                            @RequestParam(value = "pdName") String pdName){
        return ResponseEntity.ok(podService.deletePod(nsName,pdName));
    }

    /**
     * pod的查询
     * */
    @RequestMapping(value = "/getpod", method = RequestMethod.GET)
    public ResponseEntity read8spod(@RequestParam(value = "nsName") String nsName,
                                       @RequestParam(value = "pdName") String pdName){
        return ResponseEntity.ok(podService.readPod(nsName,pdName));
    }


    /**
     * 列出当前可用节点
    * */
    @RequestMapping(value = "/getNodes", method = RequestMethod.GET)
    public ResponseEntity getNodes(){
        NodeList nodeList = new NodeList();
        try {
            nodeList = Common._kube.nodes().list();
            System.out.println("list sucess");
        }catch (Exception e){
            System.out.println("list failed");
        }
        return ResponseEntity.ok(nodeList);
    }

    /**
     * 列出当前命令空间
     * */
    @RequestMapping(value = "/getNameSpaces", method = RequestMethod.GET)
    public ResponseEntity getNameSpaces(){
        NamespaceList namespaceList = new NamespaceList();
        try {
            namespaceList = Common._kube.namespaces().list();
            System.out.println("list sucess");
        }catch (Exception e) {
            System.out.println("list failed");
        }
        return ResponseEntity.ok(namespaceList);
    }
}
