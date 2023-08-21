package com.example.fileservice.property;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class MinioProp {
    private String endpoint;
    private String accesskey;
    private String secretKey;

    public MinioProp(@Value("${minio.endpoint}") String endpoint, @Value("${minio.accessKeyId}") String accesskey,
                     @Value("${minio.accessKeySecret}") String secretKey) {
        this.endpoint = endpoint;
        this.accesskey = accesskey;
        this.secretKey = secretKey;
    }
}