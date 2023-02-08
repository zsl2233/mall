package com.zsl.mall.common.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
@Component
public class EsConfig {

    private final static String hosts = "127.0.0.1:9200";

    private final static String  userName = "elastic";

    private final static String  passWord = "RRBIEZL+nHf-wZEVvLES";


    @Bean
    public ElasticsearchClient elasticsearchClient() {
        HttpHost[] httpHosts = toHttpHost();
        // Create the RestClient
        RestClient restClient = RestClient.builder(httpHosts).build();
        // Create the transport with a Jackson mapper
        RestClientTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
        // create the API client
        return new ElasticsearchClient(transport);
    }

//    @Bean
//    public ElasticsearchClient elasticsearchClient(){
//        HttpHost[] httpHosts = toHttpHost();
//        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//        credentialsProvider.setCredentials(
//                AuthScope.ANY, new UsernamePasswordCredentials(userName, passWord));
//
//        RestClientBuilder builder = RestClient.builder(httpHosts);
//        builder.setRequestConfigCallback(
//                new RestClientBuilder.RequestConfigCallback() {
//                    @Override
//                    public RequestConfig.Builder customizeRequestConfig(
//                            RequestConfig.Builder requestConfigBuilder) {
//                        return requestConfigBuilder.setSocketTimeout(60000).setConnectTimeout(5000);
//                    }
//                });
//        builder.setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
//            @Override
//            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpAsyncClientBuilder) {
//
//                return httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
//            }
//        });
//        RestClient restClient = builder.build();
//        ElasticsearchTransport transport = new RestClientTransport(restClient,new JacksonJsonpMapper());
//        return new ElasticsearchClient(transport);
//    }

    private HttpHost[] toHttpHost() {
        if (!StringUtils.hasLength(hosts)) {
            throw new RuntimeException("invalid elasticsearch configuration. elasticsearch.hosts不能为空！");
        }
        // 多个IP逗号隔开
        String[] hostArray = hosts.split(",");
        HttpHost[] httpHosts = new HttpHost[hostArray.length];
        HttpHost httpHost;
        for (int i = 0; i < hostArray.length; i++) {
            String[] strings = hostArray[i].split(":");
            httpHost = new HttpHost(strings[0], Integer.parseInt(strings[1]), "http");
            httpHosts[i] = httpHost;
        }
        return httpHosts;
    }

}
