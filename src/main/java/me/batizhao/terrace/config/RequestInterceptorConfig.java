package me.batizhao.terrace.config;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import feign.Feign;
import feign.Logger;
import feign.RequestInterceptor;
import feign.httpclient.ApacheHttpClient;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import lombok.RequiredArgsConstructor;
import me.batizhao.terrace.api.TerraceLoginApi;
import me.batizhao.terrace.dto.LoginResult;
import me.batizhao.terrace.dto.R;
import me.batizhao.terrace.exception.FeignErrorDecoder;
import me.batizhao.terrace.exception.StalberFeignException;
import org.apache.http.HttpHeaders;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author batizhao
 * @date 2021/8/30
 */
@RequiredArgsConstructor
@Configuration
public class RequestInterceptorConfig {

    private final TerraceClientProperties terraceClientProperties;
    private final RedisTemplate<String, String> template;

    public static final String KEY_TOKEN = "terrace:token:data";
    public static final String KEY_EXPIRE = "terrace:token:expire";

    /**
     * 11 个小时失效，服务端 12 个小时，这里提前失效。
     */
    public static final Integer EXPIRE_HOUR = 11;

    @Bean
    @ConditionalOnProperty(name = "client.terrace.token-store-location", havingValue = "local")
    public RequestInterceptor mapTokenInterceptor() {
        Map<String, String> map = new ConcurrentHashMap<>();

        return requestTemplate -> {
            //判断本地token是否过期
            String expireTime = map.get(KEY_EXPIRE);
            if (expireTime == null || DateUtil.parse(expireTime).isBefore(new Date())) {
                LoginResult data = getLoginResult();
                map.put(KEY_TOKEN, data.getAccessToken());
                map.put(KEY_EXPIRE, DateUtil.offsetHour(new Date(), EXPIRE_HOUR).toString(DatePattern.NORM_DATETIME_MINUTE_FORMAT));
            }

            requestTemplate.header(HttpHeaders.AUTHORIZATION, map.get(KEY_TOKEN))
                    .header(HttpHeaders.CONTENT_TYPE, "application/json");
        };
    }

    @Bean
    @ConditionalOnBean(
        name = {"redisTemplate"}
    )
    @ConditionalOnProperty(name = "client.terrace.token-store-location", havingValue = "redis")
    public RequestInterceptor redisTokenInterceptor() {

        return requestTemplate -> {
            //判断本地token是否过期
            String expireTime = template.opsForValue().get(KEY_EXPIRE);
            if (expireTime == null || DateUtil.parse(expireTime).isBefore(new Date())) {
                LoginResult data = getLoginResult();
                template.opsForValue().set(KEY_TOKEN, data.getAccessToken());
                template.opsForValue().set(KEY_EXPIRE, DateUtil.offsetHour(new Date(), EXPIRE_HOUR).toString(DatePattern.NORM_DATETIME_MINUTE_FORMAT));
            }

            requestTemplate.header(HttpHeaders.AUTHORIZATION, Objects.requireNonNull(template.opsForValue().get(KEY_TOKEN)))
                    .header(HttpHeaders.CONTENT_TYPE, "application/json");
        };
    }

    /**
     * 调用 login 接口获取 token
     * @return LoginResult
     */
    private LoginResult getLoginResult() {
        TerraceLoginApi loginApi = Feign.builder()
                .client(new ApacheHttpClient())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .errorDecoder(new FeignErrorDecoder())
                .logger(new Slf4jLogger())
                .logLevel(Logger.Level.FULL)
                .target(TerraceLoginApi.class, terraceClientProperties.getUrl());

        //过期重新请求，覆盖本地
        R<LoginResult> result = loginApi.login(terraceClientProperties.getClientId(), terraceClientProperties.getClientSecret());
        if (!"000000".equals(result.getCode()) || result.getData() == null)
            throw new StalberFeignException("Error calling Terrace API.");

        return result.getData();
    }

}
