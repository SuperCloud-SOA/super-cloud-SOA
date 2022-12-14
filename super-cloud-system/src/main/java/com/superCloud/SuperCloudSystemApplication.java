package com.superCloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 系统管理
 * @author ys
 * @version 1.0
 * @date 2022/8/2 11:28
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class SuperCloudSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SuperCloudSystemApplication.class,args);
    }

}
