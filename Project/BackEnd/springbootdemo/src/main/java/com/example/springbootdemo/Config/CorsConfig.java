package com.example.springbootdemo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // 允许跨域的域名，可以是具体的域名，也可以是通配符"*"表示允许任意域名跨域
        config.addAllowedOrigin("http://localhost:8888");

        // 允许跨域的方法，如 GET、POST 等
        config.addAllowedMethod("*");

        // 允许跨域的头部信息，这里添加了 Authorization 头
        config.addAllowedHeader("*");
        // 或者只添加 Authorization 头
        // config.addAllowedHeader("Authorization");

        // 是否支持凭证，例如在跨域请求时是否发送 Cookie 等
        config.setAllowCredentials(true);

        source.registerCorsConfiguration("/title/**", config);
        source.registerCorsConfiguration("/user/**", config);
        return new CorsFilter(source);
    }
}
