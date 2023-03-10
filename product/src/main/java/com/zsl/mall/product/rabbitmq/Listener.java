package com.zsl.mall.product.rabbitmq;

import com.rabbitmq.client.Channel;
import com.zsl.mall.product.entity.BrandEntity;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
@RabbitListener(queues = {"zsl-topic-queue1"})
public class Listener {
    @RabbitHandler
    public void process(BrandEntity testMessage , Channel channel, Message mqMsg) throws IOException {
        System.out.println("消费者收到消息 testMessage : " + testMessage.toString());
        System.out.println("消费者收到消息  Channel: " + channel.toString());
        System.out.println("消费者收到消息  Message: " + mqMsg.toString());
        //channel.basicAck(mqMsg.getMessageProperties().getDeliveryTag(),false);
        //channel.basicNack(mqMsg.getMessageProperties().getDeliveryTag(),false,true);
        channel.basicReject(mqMsg.getMessageProperties().getDeliveryTag(),false);
    }
}
