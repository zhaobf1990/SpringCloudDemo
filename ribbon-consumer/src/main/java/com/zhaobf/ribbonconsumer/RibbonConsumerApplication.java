package com.zhaobf.ribbonconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//让该应用注册为Eureka客户端应用,以获得服务发现的能力
@EnableDiscoveryClient
@SpringBootApplication
//开启断路器功能
@EnableCircuitBreaker
//该注解中包含了@EnableDiscoveryClient ,@SpringBootApplication ,@EnableCircuitBreaker三个注解,这也意味头一个SpringCloud标准应用应包含服务发现以及断路器.
//@SpringCloudApplication
public class RibbonConsumerApplication {
    @Bean
//开启客户端负载均衡
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(RibbonConsumerApplication.class, args);
    }
}
