package com.zsl.mall.product.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicConfig {

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("zsl-topic-Exchange",true,false);
    }

    @Bean
    public Queue topicQueue1(){
        return new Queue("zsl-topic-queue1",true);
    }

    @Bean
    public Binding topicBing1(){
        return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with("topic.1");
    }

    @Bean
    public Queue topicQueue2(){
        return new Queue("zsl-topic-queue2",true);
    }

    @Bean
    public Binding topicBing2(){
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with("topic.*");
    }

}
