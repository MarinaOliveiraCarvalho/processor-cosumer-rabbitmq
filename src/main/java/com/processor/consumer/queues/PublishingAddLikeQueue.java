package com.processor.consumer.queues;

import com.processor.consumer.config.RabbitListenerConfiguration;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class PublishingAddLikeQueue {

    @Value("${api.rabbitmq.publishing.add-like.exchange}")
    private String nameExchange;

    @Value("${api.rabbitmq.publishing.add-like.queue}")
    private String nameQueue;

    @Value("${api.rabbitmq.publishing.add-like.routing}")
    private String nameRouting;

    @Value("${api.rabbitmq.publishing.add-like.dlq.queue}")
    private String nameDlqQueue;

    @Value("${api.rabbitmq.publishing.add-like.dlq.routing}")
    private String nameDlqRouting;

    @Value("${api.rabbitmq.publishing.add-like.dlq.delay}")
    private Long timeDlqDelay;

    @Value("${api.rabbitmq.publishing.add-like.pkl.queue}")
    private String namePklQueue;

    @Value("${api.rabbitmq.publishing.add-like.pkl.routing}")
    private String namePklRouting;


    @Bean
    public TopicExchange journeyExchange() {
        return new TopicExchange(nameExchange, true, false);
    }

    @Bean
    public Queue journeyQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put(RabbitListenerConfiguration.DEAD_LETTER_EXCHANGE_HEADER, nameExchange);
        args.put(RabbitListenerConfiguration.DEAD_LETTER_ROUTING_KEY_HEADER, nameDlqRouting);
        return new Queue(nameQueue, true, false, false, args);
    }

    @Bean
    public Binding journeyBinding() {
        return BindingBuilder.bind(journeyQueue()).to(journeyExchange()).with(nameRouting);
    }

    @Bean
    public Queue journeyQueueDLQ() {
        Map<String, Object> args = new HashMap<>();
        args.put(RabbitListenerConfiguration.DEAD_LETTER_EXCHANGE_HEADER, nameExchange);
        args.put(RabbitListenerConfiguration.DEAD_LETTER_ROUTING_KEY_HEADER, nameRouting);
        args.put(RabbitListenerConfiguration.MESSAGE_TTL_HEADER, timeDlqDelay);
        return new Queue(nameDlqQueue, true, false, false, args);
    }

    @Bean
    public Binding journeyBindingDLQ() {
        return BindingBuilder.bind(journeyQueueDLQ()).to(journeyExchange()).with(nameDlqRouting);
    }

    @Bean
    public Queue journeyQueueParkingLot() {
        return new Queue(namePklQueue, true, false, false);
    }

    @Bean
    public Binding journeyBindingParkingLot() {
        return BindingBuilder.bind(journeyQueueParkingLot()).to(journeyExchange()).with(namePklRouting);
    }
}
