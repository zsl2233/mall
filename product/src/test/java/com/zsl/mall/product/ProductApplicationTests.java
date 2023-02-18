package com.zsl.mall.product;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.TotalHits;
import co.elastic.clients.elasticsearch.core.search.TotalHitsRelation;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zsl.mall.product.entity.BrandEntity;
import com.zsl.mall.product.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RequestOptions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@Slf4j
@SpringBootTest
class BrandEntityApplicationTests {
    @Autowired
    private BrandService brandService;

    @Autowired
    private ElasticsearchClient esClient;

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
        log.info("=================esClient:{}",esClient);
    }

    @Test
    void addDocument() throws IOException {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setBrandId(1L);
        brandEntity.setName("zsl1");


        IndexRequest<BrandEntity> request = IndexRequest.of(i -> i
                .index("brand")
                .id(brandEntity.getBrandId().toString())
                .document(brandEntity)
        );

        IndexResponse response = esClient.index(request);
        log.info("===================response:{}",response);
    }

    @Test
    void readDocById() throws IOException {
        GetResponse<BrandEntity> response = esClient.get(g -> g
                        .index("brand")
                        .id("1"),
                BrandEntity.class
        );

        if (response.found()) {
            BrandEntity brandEntity = response.source();
            log.info("brand name " + brandEntity.getName());
        } else {
            log.info ("brand not found");
        }
    }
    @Test
    void searchDoc() throws IOException {
        String searchText = "zsl";

        SearchResponse<BrandEntity> response = esClient.search(s -> s
                        .index("brand")
                        .query(q -> q
                                .match(t -> t
                                        .field("name")
                                        .query(searchText)
                                )
                        ),
                BrandEntity.class
        );

        TotalHits total = response.hits().total();
        boolean isExactResult = total.relation() == TotalHitsRelation.Eq;

        if (isExactResult) {
            log.info("There are " + total.value() + " results");
        } else {
            log.info("There are more than " + total.value() + " results");
        }

        List<Hit<BrandEntity>> hits = response.hits().hits();
        for (Hit<BrandEntity> hit: hits) {
            BrandEntity brand = hit.source();
            log.info("Found product " + brand.getBrandId() + ", score " + hit.score());
        }
    }

    @Test
    public void delDoc() throws IOException {
        //设置要删除的索引、文档
        DeleteRequest deleteRequest=DeleteRequest.of(i -> i
                .index("brand")
                .id("1")
        );

        DeleteResponse response = esClient.delete(deleteRequest);
        log.info("====================delete res:{}",response);

    }

}
