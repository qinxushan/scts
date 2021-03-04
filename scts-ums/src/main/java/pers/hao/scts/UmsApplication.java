package pers.hao.scts;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author hujiahao
 */
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan(basePackages = {"pers.hao.scts.ums.mapper"})
@EnableConfigurationProperties
public class UmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(UmsApplication.class, args);
    }
}
