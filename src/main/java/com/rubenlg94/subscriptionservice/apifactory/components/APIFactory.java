package com.rubenlg94.subscriptionservice.apifactory.components;

import com.rubenlg94.subscriptionservice.apifactory.APIEndpoints;
import com.rubenlg94.subscriptionservice.apifactory.config.APIConfig;
import com.rubenlg94.subscriptionservice.apifactory.config.APIFactoryConfig;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class APIFactory {
    private static APIFactoryConfig apiFactoryConfig;

    public APIFactory(APIFactoryConfig apiFactoryConfig) {
        APIFactory.apiFactoryConfig = apiFactoryConfig;
    }

    public static APIEndpoints fromApi(String project) {
        HttpHeaders headers = new HttpHeaders();
        APIConfig apiConfig = apiFactoryConfig.getApis().get(project);
        headers.add(apiConfig.getHeaderKey(), apiConfig.getHeaderValue());
        return new APIEndpoints(headers, apiConfig.getIp());
    }
}
