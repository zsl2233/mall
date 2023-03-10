package com.zsl.mall.product.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutConfig {

    @Bean
    public FanoutExchange FanoutExchange(){
        return new FanoutExchange("zsl-fanout-Exchange",true,false);
    }

    @Bean
    public Queue FanoutQueue1(){
        return new Queue("zsl-Fanout-queue1",true);
    }

    @Bean
    public Queue FanoutQueue2(){
        return new Queue("zsl-Fanout-queue2",true);
    }


    @Bean
    public Binding FanoutBing1(){
        return BindingBuilder.bind(FanoutQueue1()).to(FanoutExchange());
    }

    @Bean
    public Binding FanoutBing2(){
        return BindingBuilder.bind(FanoutQueue2()).to(FanoutExchange());
    }


}
