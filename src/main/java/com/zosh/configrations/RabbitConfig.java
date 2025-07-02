package com.zosh.configrations;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    // Nombres de las colas y exchanges
    public static final String BOOKING_QUEUE = "booking-queue";
    public static final String BOOKING_EXCHANGE = "booking-exchange";
    public static final String BOOKING_ROUTING_KEY = "booking.created";

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
            Jackson2JsonMessageConverter converter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter);
        return rabbitTemplate;
    }

    @Bean
    public Queue bookingQueue() {
        return QueueBuilder.durable(BOOKING_QUEUE).build();
    }

    @Bean
    public DirectExchange bookingExchange() {
        return new DirectExchange(BOOKING_EXCHANGE);
    }

    @Bean
    public Binding bookingBinding() {
        return BindingBuilder
                .bind(bookingQueue())
                .to(bookingExchange())
                .with(BOOKING_ROUTING_KEY);
    }
}