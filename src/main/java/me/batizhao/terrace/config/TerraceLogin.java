package me.batizhao.terrace.config;

import feign.Feign;
import feign.Logger;
import feign.httpclient.ApacheHttpClient;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import me.batizhao.terrace.api.TerraceLoginApi;
import me.batizhao.terrace.dto.LoginResult;
import me.batizhao.terrace.dto.R;
import me.batizhao.terrace.exception.FeignErrorDecoder;
import me.batizhao.terrace.exception.StalberFeignException;

/**
 * @author batizhao
 * @date 2021/9/2
 */
public class TerraceLogin {

    /**
     * 调用 login 接口获取 token
     * @return LoginResult
     */
    public static LoginResult getLoginResult(TerraceClientProperties properties) {
        TerraceLoginApi loginApi = Feign.builder()
                .client(new ApacheHttpClient())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .errorDecoder(new FeignErrorDecoder())
                .logger(new Slf4jLogger())
                .logLevel(Logger.Level.FULL)
                .target(TerraceLoginApi.class, properties.getUrl());

        //过期重新请求，覆盖本地
        R<LoginResult> result = loginApi.login(properties.getClientId(), properties.getClientSecret());
        if (!"000000".equals(result.getCode()) || result.getData() == null)
            throw new StalberFeignException("Error calling Terrace API.");

        return result.getData();
    }

}
