package com.niupi.niuojbackendjudgeservice;

import com.niupi.niuojbackendjudgeservice.rabbitmq.InitRabbitMq;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@ComponentScan("com.niupi")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.niupi.niuojbackendserviceclient.service"})
public class NiuojBackendJudgeServiceApplication {

    public static void main(String[] args) {
//        初始化消息队列
        InitRabbitMq.doInit();
        SpringApplication.run(NiuojBackendJudgeServiceApplication.class, args);
    }

}
