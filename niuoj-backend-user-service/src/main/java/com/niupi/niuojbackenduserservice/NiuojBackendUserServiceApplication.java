package com.niupi.niuojbackenduserservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.niupi.niuojbackenduserservice.mapper")
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@ComponentScan("com.niupi")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.niupi.niuojbackendserviceclient.service"})
public class NiuojBackendUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NiuojBackendUserServiceApplication.class, args);
    }

}
