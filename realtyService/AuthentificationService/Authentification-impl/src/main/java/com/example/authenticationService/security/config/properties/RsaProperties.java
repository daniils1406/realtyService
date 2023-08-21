package com.example.authenticationService.security.config.properties;

import lombok.Data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Component
@Data
public class RsaProperties {
    @Value("${rsa.private-key}")
    private RSAPrivateKey privateKey;

    @Value("${rsa.public-key}")
    private RSAPublicKey publicKey;
}
