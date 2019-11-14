package com.bdi.lab.controller;

import com.bdi.lab.service.RcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
@RequestMapping("/replication")
public class ReplicationController {
    @Autowired
    RcService rcService;
    /**
     * k8s rc create
     * */
    @RequestMapping(value = "/createrc", method = RequestMethod.POST)
    public ResponseEntity createk8src(@RequestParam(value = "rcName") String rcName,
                                      @RequestParam(value = "lbkey") String lbkey,
                                      @RequestParam(value = "lbvalue") String lbvalue,
                                      @RequestParam(value = "replicas") int replicas,
                                      @RequestParam(value = "ctName") String ctName,
                                      @RequestParam(value = "imName") String imName,
                                      @RequestParam(value = "cnPort") int cnPort) {
        return ResponseEntity.ok(rcService.createDeployment(rcName, "default", lbkey, lbvalue, replicas, ctName, imName, cnPort));
    }
    /**
     * k8s rc delete
    * */
    @RequestMapping(value = "/deleterc", method = RequestMethod.DELETE)
    public ResponseEntity deletek8src(@RequestParam(value = "nsName") String nsName,
                                             @RequestParam(value = "rcName") String rcName){
        return ResponseEntity.ok(rcService.deleteRC(nsName, rcName));
    }
    /**
     * k8s rc read
    * */

    @RequestMapping(value = "/readrc", method = RequestMethod.GET)
    public ResponseEntity readk8src(@RequestParam(value = "rcName") String rcName){
        return ResponseEntity.ok(rcService.readRC("default", rcName));
    }

    /**
     * 获取所有的副本信息
    * */
    @RequestMapping(value = "/getAllRc", method = RequestMethod.GET)
    public ResponseEntity getAllRc(){
        return ResponseEntity.ok(rcService.getAllDeployment("default"));
    }
}
