package me.batizhao.terrace.config;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import feign.RequestTemplate;
import me.batizhao.terrace.dto.LoginResult;
import org.apache.http.HttpHeaders;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author batizhao
 * @date 2021/9/2
 */
public class TerraceLocalTokenInterceptor extends BaseTokenRequestInterceptor {

    private final Map<String, String> map = new ConcurrentHashMap<>();

    /**
     * Creates new instance of {@link TerraceLocalTokenInterceptor}.
     * @param properties the properties
     */
    protected TerraceLocalTokenInterceptor(TerraceClientProperties properties) {
        super(properties);
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        //判断本地token是否过期
        String expireTime = map.get(getProperties().getKeyExpire());
        if (expireTime == null || DateUtil.parse(expireTime).isBefore(new Date())) {
            LoginResult data = TerraceLogin.getLoginResult(getProperties());
            map.put(getProperties().getKeyToken(), data.getAccessToken());
            map.put(getProperties().getKeyExpire(), DateUtil.offsetHour(new Date(), 11).toString(DatePattern.NORM_DATETIME_MINUTE_FORMAT));
        }

        requestTemplate.header(HttpHeaders.AUTHORIZATION, map.get(getProperties().getKeyToken()))
                .header(HttpHeaders.CONTENT_TYPE, "application/json");
    }

}
