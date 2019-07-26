package com.bdi.lab.controller;

import com.bdi.lab.service.ServiceService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
        service.stopService(serviceName);
        return ResponseEntity.ok("code: 1");
    }
    @PostMapping("/startService/{serviceName}/{num}")
    public ResponseEntity startService(@PathVariable("serviceName") String serviceName,
                                       @PathVariable("num") Integer num){

        return ResponseEntity.ok("code: "+(service.startService(serviceName,num)?1:0));
    }
}
