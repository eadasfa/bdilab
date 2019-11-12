package com.bdi.lab.controller;

import com.bdi.lab.service.ServiceService;
import com.bdi.lab.service.ServiceServiceImpl;
import com.bdi.lab.utils.Common;
import io.fabric8.kubernetes.api.model.Service;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    private ServiceService service;

    /**
     * 获取所有的服务名称和状态
    * */
    @GetMapping("/getAllService")
    public ResponseEntity getService(){
        return ResponseEntity.ok(service.getServiceName());
        //return JSONObject.fromObject(service.getServiceName()).toString();
    }

    /**
     * 根据服务名称得到服务状态
    * */
    @GetMapping("/getState/{serviceName}")
    public ResponseEntity getServiceState(@PathVariable("serviceName") String serviceName) {
        return ResponseEntity.ok(service.getState(serviceName));
         //        return service.getState(serviceName);
    }

    /**
     * 根据服务名称获取服务的所有信息
    * */
    @GetMapping("/getAllInfo/{serviceName}")
    public ResponseEntity getAllInfoOfService(@PathVariable("serviceName") String serviceName) {

        return ResponseEntity.ok(service.getAllInfo(serviceName));
//        return JSONObject.fromObject(service.getAllInfo(serviceName)).toString();
    }
    /**
     * 根据服务名称停止某个服务
    * */
    @PostMapping("/stopService/{serviceName}")
    public ResponseEntity stopService(@PathVariable("serviceName") String serviceName){
        return ResponseEntity.ok(service.stopService(serviceName));
    }
    /**
     * 根据服务名称，开启某个服务并给定服务副本的数量。
    * */
    @PostMapping("/startService/{serviceName}/{num}")
    public ResponseEntity startService(@PathVariable("serviceName") String serviceName,
                                       @PathVariable("num") Integer num){

        return ResponseEntity.ok(service.startService(serviceName,num));
    }

    /**
     *根据服务名称删除某个服务
    * */
    @DeleteMapping("/deleteService/{serviceName}")
    public ResponseEntity deleteService(@PathVariable("serviceName") String serviceName){
        service.deleteService(serviceName);
        return ResponseEntity.ok("code:1");
    }

    /**
     *根据服务参数创建服务。
    * */
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


    /**
     *
    * */
    @GetMapping("/getVersionSize/{serviceName}")
    public ResponseEntity getVersionSize(@PathVariable("serviceName") String serviceName){
        return ResponseEntity.ok(service.getVersionSize(serviceName));
    }
    /**
     *根据服务参数改变服务权重
    * */
    @PostMapping("/changeWeight")
    public ResponseEntity changeWeight(@RequestBody Map<String,Object> params){
        String serviceName = (String)params.get("name");
        List<Integer> weights = (List<Integer>)params.get("weights");
        return ResponseEntity.ok("code:"+(service.changeWeight(serviceName,weights)?1:0));
    }

   /**
    * 新的创建服务的接口
   * */
    @RequestMapping(value = "/createservice", method = RequestMethod.POST)
    public ResponseEntity createk8sservice(@RequestParam(value = "srName") String srName,
                                    @RequestParam(value = "nsName") String nsName,
                                    @RequestParam(value = "lbkey") String lbkey,
                                    @RequestParam(value = "lbvalue") String lbvalue,
                                    @RequestParam(value = "cnPort") int cnPort,
                                    @RequestParam(value = "ndPort") int ndPort){
        return ResponseEntity.ok(ServiceServiceImpl.createService(srName, nsName, lbkey, lbvalue, cnPort, ndPort));
    }


    /**
     * 新的查询服务的接口
    * */
    @RequestMapping(value = "/readservice", method = RequestMethod.GET)
    public ResponseEntity readservice(@RequestParam(value = "serviceName") String serviceName){
        Service service = new Service();
        try {
            service = Common._kube.services().inNamespace("default").withName(serviceName).get();
            System.out.println("service read success");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("service read failed");
        }
        return ResponseEntity.ok(service);
    }




}
