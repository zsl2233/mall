package com.zsl.mall.product.controller;

import com.zsl.mall.common.utils.R;
import com.zsl.mall.product.entity.BrandEntity;
import com.zsl.mall.product.service.AttrService;
import com.zsl.mall.product.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("product/rabbitmq")
public class RabbitmqController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private BrandService brandService;

    @Autowired
    private AttrService attrService;

    @RequestMapping("/direct")
    public R direct(@RequestBody Map<String,String> map){
        BrandEntity brandEntity= brandService.getById(1);
        rabbitTemplate.convertAndSend("zsl-direct-Exchange",map.get("routeKey"),brandEntity, new CorrelationData(UUID.randomUUID().toString()));
        return R.ok();
    }

    @RequestMapping("/fanout")
    public R fanout(){
        BrandEntity brandEntity= brandService.getById(1);
        rabbitTemplate.convertAndSend("zsl-fanout-Exchange",null,brandEntity, new CorrelationData(UUID.randomUUID().toString()));
        return R.ok();
    }

    @RequestMapping("/topic")
    public R topic(@RequestBody Map<String,String> map){
        BrandEntity brandEntity= brandService.getById(1);
        rabbitTemplate.convertAndSend(map.get("exchange"),map.get("routeKey"),brandEntity, new CorrelationData(UUID.randomUUID().toString()));
        return R.ok();
    }


}
