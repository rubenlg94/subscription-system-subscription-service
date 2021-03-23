package com.rubenlg94.subscriptionservice.apifactory.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "api-factory")
@Validated
public class APIFactoryConfig {

    private Map<String, APIConfig> apis;

    public Map<String, APIConfig> getApis() {
        return apis;
    }

    public void setApis(Map<String, APIConfig> apis) {
        this.apis = apis;
    }

}

