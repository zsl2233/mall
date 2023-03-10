package com.zsl.mall.product.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectConfig {

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("zsl-direct-Exchange",true,false);
    }

    @Bean
    public Queue directQueue1(){
        return new Queue("zsl-direct-queue1",true);
    }

    @Bean
    public Binding directBing1(){
        return BindingBuilder.bind(directQueue1()).to(directExchange()).with("direct.1");
    }

    @Bean
    public Queue directQueue2(){
        return new Queue("zsl-direct-queue2",true);
    }

    @Bean
    public Binding directBing2(){
        return BindingBuilder.bind(directQueue2()).to(directExchange()).with("direct.2");
    }

}
