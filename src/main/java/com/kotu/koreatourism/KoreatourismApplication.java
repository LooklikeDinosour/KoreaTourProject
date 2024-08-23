package com.kotu.koreatourism;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.kotu.koreatourism.mapper")
public class KoreatourismApplication {

    public static void main(String[] args) {
        SpringApplication.run(KoreatourismApplication.class, args);
    }

}
