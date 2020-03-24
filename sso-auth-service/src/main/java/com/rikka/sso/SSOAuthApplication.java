package com.rikka.sso;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@EnableDubbo
@SpringBootApplication(scanBasePackages = {"com.rikka.sso","com.rikka.common"})
@MapperScan("com.rikka.sso.mapper")
@EnableHystrix
public class SSOAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(SSOAuthApplication.class, args);
    }
}
