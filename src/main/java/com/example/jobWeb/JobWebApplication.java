package com.example.jobWeb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Spring Boot 应用的标识
@SpringBootApplication
// mapper 接口类扫描包配置
@MapperScan(basePackages = "com.example.jobWeb.mapper")
public class JobWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobWebApplication.class, args);
    }
}
