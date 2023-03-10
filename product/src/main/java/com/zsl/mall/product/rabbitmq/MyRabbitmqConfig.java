package com.zsl.mall.product.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Nonnull;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;


@Configuration
public class MyRabbitmqConfig {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void initRabbitTemplate() {
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                System.out.println("ConfirmCallback:     "+"相关数据："+correlationData);
                System.out.println("ConfirmCallback:     "+"确认情况："+ack);
                System.out.println("ConfirmCallback:     "+"原因："+cause);
            }
        });
        rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
            @Override
            public void returnedMessage(@Nonnull ReturnedMessage returned) {
                System.out.println("ReturnCallback:     "+"消息："+returned.getMessage());
                System.out.println("ReturnCallback:     "+"回应码："+returned.getReplyCode());
                System.out.println("ReturnCallback:     "+"回应信息："+returned.getReplyText());
                System.out.println("ReturnCallback:     "+"交换机："+returned.getExchange());
                System.out.println("ReturnCallback:     "+"路由键："+returned.getRoutingKey());
            }
        });
    }


//    @Bean
//    public MessageConverter messageConverter(){
//        return new Jackson2JsonMessageConverter();
//    }

//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//    @PostConstruct
//    public void init(){
//        rabbitTemplate.setConfirmCallback(this);
//        rabbitTemplate.setReturnCallback(this);
//    }

/*    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println("ConfirmCallback:     "+"相关数据："+correlationData);
        System.out.println("ConfirmCallback:     "+"确认情况："+ack);
        System.out.println("ConfirmCallback:     "+"原因："+cause);
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.out.println("ReturnCallback:     "+"消息："+message);
        System.out.println("ReturnCallback:     "+"回应码："+replyCode);
        System.out.println("ReturnCallback:     "+"回应信息："+replyText);
        System.out.println("ReturnCallback:     "+"交换机："+exchange);
        System.out.println("ReturnCallback:     "+"路由键："+routingKey);
    }*/

}
