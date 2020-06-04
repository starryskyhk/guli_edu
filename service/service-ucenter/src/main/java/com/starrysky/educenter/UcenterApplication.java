package com.starrysky.educenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 韩坤
 * @create 2020-06-02-14:51
 * @Description:
 */
@SpringBootApplication
@ComponentScan({"com.starrysky"})
@EnableDiscoveryClient
@MapperScan("com.starrysky.educenter.mapper")
public class UcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class,args);
    }
}
