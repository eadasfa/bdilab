package com.bdi.lab;

import com.bdi.lab.test.TestApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BdilabApplication {
    public static void main(String[] args) {
        SpringApplication.run(BdilabApplication.class, args);
        TestApi testApi=new TestApi();
        testApi.testCreateNameSpace();
        testApi.testCreatePod();
        testApi.testGetPodList();
    }
}
