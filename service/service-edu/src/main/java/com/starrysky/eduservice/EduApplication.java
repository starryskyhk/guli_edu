package com.starrysky.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 韩坤
 * @create 2020-05-21-20:02
 * @Description:
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.starrysky"})
public class EduApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);
    }
}
