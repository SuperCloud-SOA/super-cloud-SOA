package superCloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 系统管理
 * @author ys
 * @version 1.0
 * @date 2022/8/2 11:28
 */
@EnableDiscoveryClient
@SpringBootApplication
public class SuperCloudGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SuperCloudGatewayApplication.class,args);
    }

}
