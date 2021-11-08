package me.batizhao.terrace.config;

import feign.Feign;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

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
    @ConditionalOnProperty(name = "pecado.terrace.token-store-location", havingValue = "memory")
    public TerraceLocalTokenInterceptor terraceLocalTokenInterceptor(TerraceClientProperties properties) {
        return new TerraceLocalTokenInterceptor(properties);
    }

    @Bean
    @ConditionalOnBean(RedisTemplate.class)
    @ConditionalOnProperty(name = "pecado.terrace.token-store-location", havingValue = "redis")
    public TerraceRedisTokenInterceptor terraceRedisTokenInterceptor(TerraceClientProperties properties) {
        return new TerraceRedisTokenInterceptor(properties);
    }

}
