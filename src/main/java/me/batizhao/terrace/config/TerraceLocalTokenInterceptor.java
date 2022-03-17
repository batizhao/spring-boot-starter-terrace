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
        //获取本地token过期时间
        String expireTime = map.get(getProperties().getKeyExpire());
        //如果为空（第一次），或者已经过期
        if (expireTime == null || DateUtil.parse(expireTime).isBefore(new Date())) {
            //重新发起登录请求
            LoginResult data = TerraceLogin.getLoginResult(getProperties());
            //保存 token 和 过期时间到本地
            map.put(getProperties().getKeyToken(), data.getAccessToken());
            //流程平台是12小时过期，这里使用12-1，预防时间不同步
            map.put(getProperties().getKeyExpire(), DateUtil.offsetHour(new Date(), 11).toString(DatePattern.NORM_DATETIME_MINUTE_FORMAT));
        }

        //放到请求头中
        requestTemplate.header(HttpHeaders.AUTHORIZATION, map.get(getProperties().getKeyToken()))
                .header(HttpHeaders.CONTENT_TYPE, "application/json");
    }

}
