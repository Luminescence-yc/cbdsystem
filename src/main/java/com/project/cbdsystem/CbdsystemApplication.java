package com.project.cbdsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@ComponentScan("com.project")
@MapperScan("com.project.mapper")
@EnableTransactionManagement
@SpringBootApplication
@EnableScheduling

public class CbdsystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(CbdsystemApplication.class, args);
    }

}
