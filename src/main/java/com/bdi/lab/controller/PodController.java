package com.bdi.lab.controller;

import com.bdi.lab.service.PodService;
import com.bdi.lab.utils.Common;
import io.fabric8.kubernetes.api.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/pod")
public class PodController {
    @Autowired
    PodService podService;

    /**
     * Pod的创建
    * */
    @RequestMapping(value = "/createpod", method = RequestMethod.GET)
    public ResponseEntity createk8spod(@RequestParam(value = "podName") String pdName,
                                       @RequestParam(value = "labelkey") String labelkey,
                                       @RequestParam(value = "labelvalue") String labelvalue,
                                       @RequestParam(value = "containerName") String containerName,
                                       @RequestParam(value = "imageName") String imageName,
                                       @RequestParam(value = "containerPort") int containerPort){
        return ResponseEntity.ok(podService.createPod("default",pdName,labelkey,labelvalue, containerName, imageName,containerPort));
    }

    /**
     * pod的删除
    * */
    @RequestMapping(value = "/deletepod", method = RequestMethod.DELETE)
    public ResponseEntity deletek8spod(
                            @RequestParam(value = "pdName") String pdName){
        return ResponseEntity.ok(podService.deletePod("default",pdName));
    }

    /**
     * pod的查询
     * */
    @RequestMapping(value = "/getpod", method = RequestMethod.GET)
    public ResponseEntity read8spod(@RequestParam(value = "pdName") String pdName){
        return ResponseEntity.ok(podService.readPod("default",pdName));
    }


    /**
     * 列出当前可用节点
    * */
    @RequestMapping(value = "/getPods", method = RequestMethod.GET)
    public ResponseEntity getPods(){
        System.out.println(Common.IP);
        List<Map<Object,Object>> result=new ArrayList<>();
        List<Pod> podList = new ArrayList<>();
        try {
            podList = Common._kube.pods().list().getItems();
            for(Pod pod:podList){
                Map<Object,Object> map=new HashMap<>();
                map.put("podName",pod.getMetadata().getName());
                // 对象所处的生命周期阶段“Pending”（创建中）“Running”“Active”（正在运行中）或“Terminated”（已终结）
                map.put("phase",pod.getStatus().getPhase());
                // pod运行的节点IP
                map.put("hostIp",pod.getStatus().getHostIP());
                // pod创建时间
                map.put("startTime",pod.getStatus().getStartTime());
                List<Map<Object,Object>> podContainers=new ArrayList<>();

                List<ContainerStatus> containerStatuses=pod.getStatus().getContainerStatuses();
                for(ContainerStatus c:containerStatuses){
                    Map<Object,Object> container=new HashMap<>();
                    container.put("image",c.getImage());
                    container.put("name",c.getName());
                    container.put("reader",c.getReady());
                    podContainers.add(container);
                }
                map.put("containers",podContainers);
                result.add(map);
            }
            System.out.println("list sucess");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("list failed");
        }
        return ResponseEntity.ok(result);
    }

    /**
     * 列出当前命令空间
     * */
    @RequestMapping(value = "/getNameSpaces", method = RequestMethod.GET)
    public ResponseEntity getNameSpaces(){
        List<Namespace> namespaceList = new ArrayList<>();
        try {
            namespaceList = Common._kube.namespaces().list().getItems();
            List<Map<Object,Object>> list=new ArrayList<>();
            for(Namespace namespace:namespaceList){
                Map<Object,Object> map=new HashMap<>();
                // 命名空间名字
                map.put("namespaceName",namespace.getMetadata().getName());
                // 创建时间
                map.put("creationTimestamp",namespace.getMetadata().getCreationTimestamp());
                // 状态
                map.put("phase",namespace.getStatus().getPhase());
                list.add(map);
            }
            System.out.println("list sucess");
            return ResponseEntity.ok(list);
        }catch (Exception e) {
            System.out.println("list failed");
            return ResponseEntity.ok(null);
        }
    }
}
