package com.zhaobf.ribbonconsumer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {
    Logger logger = LoggerFactory.getLogger(HelloService.class);
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "helloFallback")
    public String helloService() {
        long start = System.currentTimeMillis();
        String result = restTemplate.getForEntity("http://hello-server/hello2", String.class).getBody();
        long end = System.currentTimeMillis();
        logger.info("ThreadId:{}    Spend time : {} ms   result:{}", Thread.currentThread().getId(), end - start, result);
        return result;
    }


    public String helloFallback() {
        logger.info("ThreadId:{}     error", Thread.currentThread().getId());
        return "error";
    }
}
