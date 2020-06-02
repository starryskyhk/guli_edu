package com.starrysky.educms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 韩坤
 * @create 2020-06-01-15:32
 * @Description:
 */
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = {"com.starrysky"})
@MapperScan("com.starrysky.educms.mapper")
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class,args);
    }
}
