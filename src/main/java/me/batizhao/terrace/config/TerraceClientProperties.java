package me.batizhao.terrace.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author batizhao
 * @date 2021/6/11
 */
@Data
@ConfigurationProperties(prefix = "client.terrace")
public class TerraceClientProperties {

    /**
     * 是否开启
     */
    private Boolean enabled;

    /**
     * 流程平台API地址
     */
    private String url;

    /**
     * 客户端ID
     */
    private String clientId;

    /**
     * 客户端Secret
     */
    private String clientSecret;

    /**
     * Token 存储方式（local、redis）
     */
    private String tokenStoreLocation;

    /**
     * Token 存储方式（local、redis）
     */
    private String keyToken;

    /**
     * Token 存储方式（local、redis）
     */
    private String keyExpire;

}
