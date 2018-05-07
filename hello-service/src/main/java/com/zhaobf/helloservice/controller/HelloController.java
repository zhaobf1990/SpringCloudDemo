package com.zhaobf.helloservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class HelloController {
    private  final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DiscoveryClient client;


    @RequestMapping(value = "/hello" ,method = RequestMethod.GET)
    public String index() {
        ServiceInstance instance = client.getLocalServiceInstance();
        logger.info("/hello, host: {}  , service_id: {}", instance.getHost(), instance.getServiceId());
        return "Hello World";
    }


    /**
     * 请求会被挂起0~3000毫秒
     * @return
     * @throws InterruptedException
     */
    @RequestMapping(value = "/hello2" ,method = RequestMethod.GET)
    public String index2() throws InterruptedException {
        ServiceInstance instance = client.getLocalServiceInstance();
        int sleepTime = new Random().nextInt(4000);
        logger.info("sleepTime:{}ms", sleepTime);
        Thread.sleep(sleepTime);
        logger.info("/hello, host: {}  , service_id: {}", instance.getHost(), instance.getServiceId());
        return "Hello World";
    }
}
