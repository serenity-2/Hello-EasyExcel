package com.google.helloeasyexcel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HelloEasyExcelApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloEasyExcelApplication.class, args);
    }

}
