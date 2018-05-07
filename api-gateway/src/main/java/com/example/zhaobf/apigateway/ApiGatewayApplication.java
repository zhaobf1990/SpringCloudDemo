package com.example.zhaobf.apigateway;

import com.example.zhaobf.apigateway.filter.AccessFilter;
import com.sun.xml.internal.bind.AccessorFactoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@SpringCloudApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}


	@Bean
	public AccessFilter accessFilter() {
		return new AccessFilter();
	}
}
