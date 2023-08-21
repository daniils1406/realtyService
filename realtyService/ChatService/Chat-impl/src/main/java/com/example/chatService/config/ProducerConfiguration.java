package com.example.chatService.config;

import com.example.chatService.model.jooq.schema.tables.pojos.MessageEntity;
import com.example.chatService.model.jooq.schema.tables.pojos.NotificationEntity;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class ProducerConfiguration {

    @Value("${spring.kafka.server}")
    private String server;

    @Bean
    public ProducerFactory<String, MessageEntity> producerMessageFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigurations());
    }

    @Bean
    public ProducerFactory<String, NotificationEntity> producerNotificationFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigurations());
    }

    @Bean
    public Map<String, Object> producerConfigurations() {
        Map<String, Object> configurations = new HashMap<>();
        configurations.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, server);
        configurations.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configurations.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return configurations;
    }

    @Bean
    public KafkaTemplate<String, MessageEntity> kafkaMessageTemplate() {
        return new KafkaTemplate<>(producerMessageFactory());
    }

    @Bean
    public KafkaTemplate<String, NotificationEntity> kafkaNotificationTemplate() {
        return new KafkaTemplate<>(producerNotificationFactory());
    }
}