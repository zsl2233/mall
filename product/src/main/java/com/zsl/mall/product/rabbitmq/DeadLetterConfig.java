package com.zsl.mall.product.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class DeadLetterConfig {

    @Bean
    public DirectExchange DeadLetterExchange(){
        return new DirectExchange("DeadLetterExchange",true,false);
    }

    @Bean
    public Queue DeadLetterQueue(){
        return new Queue("DeadLetterQueue",true);
    }

    @Bean
    public Binding DeadLetterBing(){
        return BindingBuilder.bind(DeadLetterQueue()).to(DeadLetterExchange()).with(("deadLetter"));
    }

    @Bean
    public DirectExchange DelayExchange(){
        return new DirectExchange("DelayExchange",true,false);
    }

    @Bean
    public Queue DelayQueue(){
        HashMap<String, Object> map = new HashMap<>(2);
        // 配置当前队列绑定的死信交换器
        map.put("x-dead-letter-exchange","DeadLetterExchange");
        // 配置当前队列的死信队列路由key，如果不设置默认为当前队列的路由key
        map.put("x-dead-letter-routing-key","deadLetter");
        // x-message-ttl  声明队列的TTL
        map.put("x-message-ttl",10000);
        return new Queue("DelayQueue",true,false,false,map);
    }




    @Bean
    public Binding DelayBing(){
        return BindingBuilder.bind(DelayQueue()).to(DelayExchange()).with("delay");
    }


}
