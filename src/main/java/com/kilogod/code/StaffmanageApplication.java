package com.kilogod.code;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.kilogod.code.mapper")
public class StaffmanageApplication {

    public static void main(String[] args) {
        SpringApplication.run(StaffmanageApplication.class, args);
    }

}
