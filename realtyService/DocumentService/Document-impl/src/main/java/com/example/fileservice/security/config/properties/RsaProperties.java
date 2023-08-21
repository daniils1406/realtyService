package com.example.fileservice.security.config.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPublicKey;

@Component
@Data
public class RsaProperties {
    @Value("${rsa.public-key}")
    private RSAPublicKey publicKey;
}
