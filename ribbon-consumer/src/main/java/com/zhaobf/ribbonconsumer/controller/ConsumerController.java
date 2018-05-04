package com.zhaobf.ribbonconsumer.controller;

import com.zhaobf.ribbonconsumer.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    HelloService helloService;
    @RequestMapping(value = "/ribbon-consumer", method = RequestMethod.GET)
    public String helloConsumer() {
        //这里的访问地址是服务名(hello-server),而不是具体的地址.
        return restTemplate.getForEntity("http://hello-server/hello", String.class).getBody();
    }

    /**
     * 采用断路器的方式
     * @return
     */
    @RequestMapping(value = "/ribbon-consumer2", method = RequestMethod.GET)
    public String helloConsumer2() {
        return helloService.helloService();
    }
}
