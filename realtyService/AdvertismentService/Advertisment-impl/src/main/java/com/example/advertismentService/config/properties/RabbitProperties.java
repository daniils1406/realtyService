package com.example.advertismentService.config.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;


@Validated
@Component
@Data
public class RabbitProperties {
    @Value("${service-rabbit.exchangeNotification-name}")
    private String routingKeyNotification;
    @Value("${service-rabbit.queueNotification-name}")
    private String queueNameNotification;
    @Value("${service-rabbit.routingNotification-key}")
    private String exchangeNameNotification;
}
