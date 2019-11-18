package com.bdi.lab.controller;

import com.bdi.lab.service.RcService;
import com.bdi.lab.service.ServiceService;
import com.bdi.lab.service.ServiceServiceImpl;
import com.bdi.lab.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/replication")
public class ReplicationController {
    @Autowired
    RcService rcService;
    /**
     * k8s rc create
     * */
    @Autowired
    ServiceService serviceService;
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

    /**
     *
     * @param rcName 使用的是副本控制器的名字
     * @param num  数量
     * @return 直接返回了 code：1
     */
    @GetMapping("/updateServiceNumber")
    public ResponseEntity updateServiceNumber(
            @RequestParam(value = "rcName") String rcName,
            @RequestParam(value="num") int num){
        serviceService.updateReplicas("default",rcName,num);
//        new ServiceServiceImpl().updateReplicas("default",rcName,num);
        return ResponseEntity.ok("code:1");
    }

    /**
     *
     * @param rc Name使用的是副本控制器的名字
     * @return 副本数量  数字类型字符串
     */
    @GetMapping("/getServiceNumber")
    public ResponseEntity getServiceNumber(
            @RequestParam(value = "rcName") String rcName
            ){
//        serviceService.updateReplicas("default",rcName,num);
        int n = Common._kube.apps().deployments().inNamespace("default").withName(rcName)
                .get().getSpec().getReplicas().intValue();
        System.out.println(n);

        return ResponseEntity.ok(n+"");
    }


//    public static void main(String[] args) {
//        ReplicationController rc = new ReplicationController();
////        rc.updateServiceNumber("task-v1",1);
//        System.out.println(rc.getServiceNumber("arms-v1"));
//
//    }

}
