package com.example.authenticationService;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@EnableDiscoveryClient
@SpringBootApplication
@EnableRedisRepositories
public class AdvertisementApplication {


    public static void main(String[] args) {
        SpringApplication.run(AdvertisementApplication.class, args);
    }
}
