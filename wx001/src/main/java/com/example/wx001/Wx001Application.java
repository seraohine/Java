package com.example.wx001;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.wx001.mapper")
public class Wx001Application {

    public static void main(String[] args) {
        SpringApplication.run(Wx001Application.class, args);
    }

}
