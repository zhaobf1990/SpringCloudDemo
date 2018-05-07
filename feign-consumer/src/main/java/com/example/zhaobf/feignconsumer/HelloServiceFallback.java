package com.example.zhaobf.feignconsumer;

import org.springframework.stereotype.Component;

@Component()
public class HelloServiceFallback implements HelloService {


    @Override
    public String hello() {
        return "feign-error";
    }

    @Override
    public String hello2() {
        return "feign-error2";
    }
}
