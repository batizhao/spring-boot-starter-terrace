package me.batizhao.terrace.config;

import feign.Feign;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author batizhao
 * @date 2021/9/2
 */
@RequiredArgsConstructor
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(TerraceClientProperties.class)
@ConditionalOnClass(Feign.class)
public class TerraceTokenAutoConfiguration {

    @Bean
    @ConditionalOnProperty(name = "client.terrace.token-store-location", havingValue = "local")
    public TerraceLocalTokenInterceptor terraceLocalTokenInterceptor(TerraceClientProperties terraceClientProperties) {
        return new TerraceLocalTokenInterceptor(terraceClientProperties);
    }

    @Bean
    @ConditionalOnProperty(name = "client.terrace.token-store-location", havingValue = "redis")
    public TerraceRedisTokenInterceptor terraceRedisTokenInterceptor(TerraceClientProperties properties) {
        return new TerraceRedisTokenInterceptor(properties);
    }

}
