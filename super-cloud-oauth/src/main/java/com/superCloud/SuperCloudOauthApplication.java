package com.superCloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * oauth2认证服务
 * @author ys
 * @version 1.0
 * @date 2022/8/2 17:36
 */
@EnableDiscoveryClient
@SpringBootApplication
public class SuperCloudOauthApplication {

    public static void main(String[] args) {
        SpringApplication.run(SuperCloudOauthApplication.class,args);
    }


}
