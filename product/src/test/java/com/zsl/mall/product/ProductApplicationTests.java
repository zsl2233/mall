package com.zsl.mall.product;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zsl.mall.product.entity.BrandEntity;
import com.zsl.mall.product.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class ProductApplicationTests {
    @Autowired
    private BrandService brandService;

    @Autowired
    private ElasticsearchClient client;

    @Test
    void create() {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setName("苹果");
        brandEntity.setDescript("水果");
        brandService.save(brandEntity);
        log.info("插入成功");
    }

    @Test
    void query() { ;
        BrandEntity brandEntity = brandService.getOne(
                new LambdaQueryWrapper<BrandEntity>().eq(BrandEntity::getName,"苹果")
        );
        log.info("{}",brandEntity);
    }

    @Test
    void es(){
        log.info("=================esClient:{}",client);
    }

}
