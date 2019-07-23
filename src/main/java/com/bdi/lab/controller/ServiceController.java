package com.bdi.lab.controller;

import com.bdi.lab.service.ServiceService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ServiceController {

    @Autowired
    private ServiceService service;

    @GetMapping("/getAllService")
    public String getService(){
        return JSONObject.fromObject(service.getServiceName()).toString();
    }
    @GetMapping("/getState/{serviceName}")
    public String getServiceState(@PathVariable("serviceName") String serviceName) {
        return service.getState(serviceName);
    }
    @GetMapping("/getAllInfo/{serviceName}")
    public String getAllInforOfService(@PathVariable("serviceName") String serviceName) {
        return JSONObject.fromObject(service.getAllInfo(serviceName)).toString();
    }
}
