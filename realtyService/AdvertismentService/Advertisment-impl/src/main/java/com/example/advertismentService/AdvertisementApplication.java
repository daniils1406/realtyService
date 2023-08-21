package com.example.advertismentService;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AdvertisementApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdvertisementApplication.class, args);
    }
}
