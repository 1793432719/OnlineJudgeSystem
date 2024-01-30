package com.niupi.niuojbackendquestionservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.niupi.niuojbackendquestionservice.mapper")
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@ComponentScan("com.niupi")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.niupi.niuojbackendserviceclient.service"})
public class NiuojBackendQuestionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NiuojBackendQuestionServiceApplication.class, args);
    }

}
