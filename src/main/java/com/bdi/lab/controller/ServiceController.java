package com.bdi.lab.controller;

import com.bdi.lab.service.ServiceService;
import com.bdi.lab.service.ServiceServiceImpl;
import com.bdi.lab.utils.Common;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    private ServiceService service;

    @GetMapping("/getAllService")
    public ResponseEntity getService(){
        return ResponseEntity.ok(service.getServiceName());
        //return JSONObject.fromObject(service.getServiceName()).toString();
    }
    @GetMapping("/getState/{serviceName}")
    public ResponseEntity getServiceState(@PathVariable("serviceName") String serviceName) {
        return ResponseEntity.ok(service.getState(serviceName));
//        return service.getState(serviceName);
    }
    @GetMapping("/getAllInfo/{serviceName}")
    public ResponseEntity getAllInfoOfService(@PathVariable("serviceName") String serviceName) {

        return ResponseEntity.ok(service.getAllInfo(serviceName));
//        return JSONObject.fromObject(service.getAllInfo(serviceName)).toString();
    }
    @PostMapping("/stopService/{serviceName}")
    public ResponseEntity stopService(@PathVariable("serviceName") String serviceName){
        return ResponseEntity.ok(service.stopService(serviceName));
    }
    @PostMapping("/startService/{serviceName}/{num}")
    public ResponseEntity startService(@PathVariable("serviceName") String serviceName,
                                       @PathVariable("num") Integer num){

        return ResponseEntity.ok(service.startService(serviceName,num));
    }

    @DeleteMapping("/deleteService/{serviceName}")
    public ResponseEntity deleteService(@PathVariable("serviceName") String serviceName){
        service.deleteService(serviceName);
        return ResponseEntity.ok("code:1");
    }
    @PostMapping("/createService")
    public ResponseEntity createService(@RequestBody Map<String,Object> params){

        String name = (String)params.get("name");
        String namespace = Common.NAMESPACE;
        Map<String,String> labelsMap = (Map<String,String>)params.get("labels");
        Map<String,String> selectorMap = (Map<String,String>)params.get("selector");
        Integer port = (Integer)params.get("port");
        Integer nodePort = (Integer) params.get("nodePort");
        String type = (String) params.get("type");

        return ResponseEntity.ok(
                service.createService(namespace,selectorMap,type,name,port,nodePort,labelsMap)
        );
    }




}
