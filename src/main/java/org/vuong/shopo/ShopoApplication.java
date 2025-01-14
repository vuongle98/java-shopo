package org.vuong.shopo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableConfigurationProperties
@ConfigurationPropertiesScan
public class ShopoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopoApplication.class, args);
    }

}
