package com.bdi.lab.controller;


import com.bdi.lab.utils.Common;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/setting")
public class SettingController {
    @GetMapping("getConfig")
    public ResponseEntity getConfig(@RequestParam(value = "ip")String ip,
                                    @RequestParam(value = "username")String username,
                                    @RequestParam(value = "password")String password){
        Common.IP=ip;
        Common.USERNAME=username;
        Common.PASSWORD=password;
        return ResponseEntity.ok("设置成功!");

    }
}
