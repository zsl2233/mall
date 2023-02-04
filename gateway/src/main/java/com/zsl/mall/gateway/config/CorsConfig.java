package com.zsl.mall.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * springboot跨域配置
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsWebFilter corsWebFilter(){
        // 配置源对象
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        configurationSource.registerCorsConfiguration("/**", buildConfig());
        return new CorsWebFilter(configurationSource);
    }

    private CorsConfiguration buildConfig() {
        // cors跨域配置对象
        CorsConfiguration configuration = new CorsConfiguration();
        //configuration.addAllowedOrigin("*"); //设置允许访问的网络
        configuration.addAllowedOriginPattern("*");
        configuration.setAllowCredentials(true); // 设置是否从服务器获取cookie
        configuration.addAllowedMethod("*"); // 设置请求方法 * 表示任意
        configuration.addAllowedHeader("*"); // 所有请求头信息 * 表示任意
        return configuration;
    }
}