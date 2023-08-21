package com.example.authenticationService.config.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;


@Validated
@Component
@Data
public class RabbitProperties {
    @Value("${service-rabbit.exchangeRegistration-name}")
    private String routingKeyRegistration;
    @Value("${service-rabbit.queueRegistration-name}")
    private String queueNameRegistration;
    @Value("${service-rabbit.routingRegistration-key}")
    private String exchangeNameRegistration;

    @Value("${service-rabbit.exchangeResetPassword-name}")
    private String routingKeyResetPassword;
    @Value("${service-rabbit.queueResetPassword-name}")
    private String queueNameResetPassword;
    @Value("${service-rabbit.routingResetPassword-key}")
    private String exchangeNameResetPassword;

    @Value("${service-rabbit.exchangeResetLogin-name}")
    private String routingKeyResetLogin;
    @Value("${service-rabbit.queueResetLogin-name}")
    private String queueNameResetLogin;
    @Value("${service-rabbit.routingResetLogin-key}")
    private String exchangeNameResetLogin;

    @Value("${service-rabbit.exchangeNotification-name}")
    private String routingKeyNotification;
    @Value("${service-rabbit.queueNotification-name}")
    private String queueNameNotification;
    @Value("${service-rabbit.routingNotification-key}")
    private String exchangeNameNotification;
}
