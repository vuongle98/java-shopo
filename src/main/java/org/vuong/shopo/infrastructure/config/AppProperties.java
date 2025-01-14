package org.vuong.shopo.infrastructure.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("shopo")
@Getter
@Setter
public class AppProperties {

    private String clientId;
    private String clientSecret;
    private String cacheManagerName;
}
