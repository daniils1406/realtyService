package com.example.advertismentService.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import com.example.advertismentService.config.properties.RabbitProperties;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.OptionalValidatorFactoryBean;

@Configuration
@RequiredArgsConstructor
public class RabbitConfig {

    private final RabbitProperties properties;
    private final RabbitTemplate template;
    private final ObjectMapper objectMapper;

    @Bean
    public Exchange exchangeNotification() {
        return ExchangeBuilder.directExchange(properties.getExchangeNameNotification()).build();
    }

    @Bean
    public Queue queueNotification() {
        return QueueBuilder
                .durable(properties.getQueueNameNotification())
                .withArgument("x-dead-letter-exchange", properties.getExchangeNameNotification())
                .build();
    }

    @Bean
    public Binding bindingNotification() {
        return BindingBuilder.bind(queueNotification()).to(exchangeNotification())
                .with(properties.getRoutingKeyNotification()).noargs();
    }


    @Bean
    public RetryTemplate getRetryTemplate() {
        FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
        fixedBackOffPolicy.setBackOffPeriod(3000L);

        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(3);

        RetryTemplate retryTemplate = new RetryTemplate();
        retryTemplate.setBackOffPolicy(fixedBackOffPolicy);
        retryTemplate.setRetryPolicy(retryPolicy);

        return retryTemplate;
    }

    @Bean
    public Connection getConnection() {
        return template.getConnectionFactory().createConnection();
//        return connectionFactory().createConnection();
    }

//    @Bean
//    public ConnectionFactory connectionFactory(){
//        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
//        connectionFactory.setHost(RABBITMQ_HOST);
//        connectionFactory.setPort(RABBITMQ_PORT);
//        connectionFactory.setUsername(RABBITMQ_USERNAME);
//        connectionFactory.setPassword(RABBITMQ_PASSWORD);
//        return connectionFactory;
//    }

    @Bean
    public void configureRabbitTemplate() {
        template.setMessageConverter(new Jackson2JsonMessageConverter(objectMapper));
    }

    @Bean
    public Validator amqpValidator() {
        return new OptionalValidatorFactoryBean();
    }

    @Bean
    public Channel getChannel() {
        return getConnection().createChannel(false);
    }

}
