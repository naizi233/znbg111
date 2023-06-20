package com.yzn.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.yzn.server.mapper")
@ComponentScan("com.yzn")
@EnableScheduling
public class EworkApplication {
    public static void main(String[] args) {
        SpringApplication.run(EworkApplication.class,args);
    }
}
