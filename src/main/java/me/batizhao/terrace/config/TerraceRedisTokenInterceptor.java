package me.batizhao.terrace.config;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import feign.RequestTemplate;
import me.batizhao.terrace.dto.LoginResult;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Date;
import java.util.Objects;

/**
 * @author batizhao
 * @date 2021/9/2
 */
public class TerraceRedisTokenInterceptor extends BaseTokenRequestInterceptor {

    @Autowired
    private RedisTemplate<String, String> template;

    /**
     * Creates new instance of {@link TerraceRedisTokenInterceptor}.
     * @param properties the properties
     */
    protected TerraceRedisTokenInterceptor(TerraceClientProperties properties) {
        super(properties);
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        //判断本地token是否过期
        String expireTime = template.opsForValue().get(getProperties().getKeyExpire());
        if (expireTime == null || DateUtil.parse(expireTime).isBefore(new Date())) {
            LoginResult data = TerraceLogin.getLoginResult(getProperties());
            template.opsForValue().set(getProperties().getKeyToken(), data.getAccessToken());
            template.opsForValue().set(getProperties().getKeyExpire(), DateUtil.offsetHour(new Date(), 11).toString(DatePattern.NORM_DATETIME_MINUTE_FORMAT));
        }

        requestTemplate.header(HttpHeaders.AUTHORIZATION, Objects.requireNonNull(template.opsForValue().get(getProperties().getKeyToken())))
                .header(HttpHeaders.CONTENT_TYPE, "application/json");

    }
}
