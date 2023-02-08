package com.zsl.mall.product.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@Slf4j
@SpringBootTest
public class EsTest {
    @Autowired
    private ElasticsearchClient elasticsearchClient;
    @Test
    void createIndex() throws IOException {
        CreateIndexResponse response = elasticsearchClient.indices().create(c -> c.index("test_java_index_1"));
        log.info("createIndex方法，acknowledged={}", response.acknowledged());
    }
}
