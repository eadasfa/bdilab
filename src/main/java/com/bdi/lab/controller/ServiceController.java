package com.bdi.lab.controller;

import com.bdi.lab.service.ServiceService;
import com.bdi.lab.service.ServiceServiceImpl;
import com.bdi.lab.utils.Common;
import com.bdi.lab.utils.ShellExec;
import io.fabric8.kubernetes.api.model.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bdi.lab.utils.ShellExec.getRecoverTime;

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
    @GetMapping("/stopService/{serviceName}")
    public ResponseEntity stopService(@PathVariable("serviceName") String serviceName){
        return ResponseEntity.ok(service.stopService(serviceName));
    }
    /**
     * 根据服务名称，开启某个服务并给定服务副本的数量。
     * */
    @GetMapping("/startService/{serviceName}/{num}")
    public ResponseEntity startService(@PathVariable("serviceName") String serviceName,
                                       @PathVariable("num") Integer num){

        num = 1;
        return ResponseEntity.ok(service.startService(serviceName,num));
    }

    /**
     *根据服务名称删除某个服务
     * */
    @DeleteMapping("/deleteService/{serviceName}")
    public ResponseEntity deleteService(@PathVariable("serviceName") String serviceName){
        System.out.println(serviceName);
        service.deleteService(serviceName);
        Map<Object,Object> result=new HashMap<>();
        result.put("code",1);
        result.put("message","删除成功!");
        return ResponseEntity.ok(result);
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
     *根据服务名称得到版本的数量
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
    @RequestMapping(value = "/createservice", method = RequestMethod.GET)
    public ResponseEntity createk8sservice(@RequestParam(value = "srName") String srName,
                                           @RequestParam(value = "lbkey") String lbkey,
                                           @RequestParam(value = "lbvalue") String lbvalue,
                                           @RequestParam(value = "cnPort") int cnPort
                                           //, @RequestParam(value = "ndPort",required = false) int ndPort
    ){
        return ResponseEntity.ok(ServiceServiceImpl.createService(srName, "default", lbkey, lbvalue, cnPort,0));
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


    @GetMapping("/testWeight")
    public ResponseEntity testWeight(@RequestParam("name") String name, @RequestParam("firstWeight")
            int firstWeight,@RequestParam("secondWeight") int secondWeight){
        List<Integer> weights = new ArrayList<>();
        weights.add(firstWeight);
        weights.add(secondWeight);
        service.changeWeight(name,weights);
        String cmd = " sh /home/script/visit.sh ";
        String result = ShellExec.execute(cmd).toString();
        return ResponseEntity.ok(result);
    }


    /**
     *根据服务名称，模拟故障恢复时间
     * */
    @GetMapping("/revocerTimeTest")
    public ResponseEntity revocerTimeTest(@RequestParam("name") String name){
        String time=getRecoverTime(name);
        return ResponseEntity.ok("服务的故障恢复时间为:"+time+"ms");
    }


    /**
     *根据服务名称，设置服务的优先级（总共有三个值）:high middle low
     * */
    @GetMapping("/settingPriority")
    public ResponseEntity settingPriority(@RequestParam("name") String name,
                                          @RequestParam("priority") String priority){
        String result=service.change_priority(name,priority);
        return ResponseEntity.ok(result);
    }

    /**
     *
     * @param virtualServiceName  实际就是service name
     * @return 返回一个map类型的字符串{"v1":（v1的权重 数字），"v2": （v2的权重 数字）}
     */
    @GetMapping("/getWeight")
    public ResponseEntity getWeight(
            @RequestParam("virtualServiceName") String virtualServiceName){
        return ResponseEntity.ok(service.get_weight(virtualServiceName));
    }
    /**
     *
     * @param deployName  使用的是副本控制器的名字
     * @return 返回了一个String类型 即 high low middle
     */
    @GetMapping("/getPriority")
    public ResponseEntity getPriority(
            @RequestParam("deployName") String deployName){
        String result=service.get_priority(deployName);
        return ResponseEntity.ok(result);
    }
    /**
     *根据服务名称，设置服务的优先级（总共有三个值）:high middle low
     * */
    @GetMapping("/getIP")
    public ResponseEntity getIP(){
        return ResponseEntity.ok("http://"+Common.IP+":32000");
    }

}
