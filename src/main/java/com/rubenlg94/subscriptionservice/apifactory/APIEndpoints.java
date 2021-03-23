package com.rubenlg94.subscriptionservice.apifactory;

import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class APIEndpoints {

    private static final RestTemplate restTemplate;

    static {
        restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(HttpClientBuilder.create().build()));
    }

    private final HttpHeaders headers;
    private final String ip;

    public APIEndpoints(HttpHeaders headers, String ip) {
        this.headers = headers;
        this.ip = ip;
    }

    public void get(String endpoint, Map<String, List<String>> queryParams) {
        restTemplate.exchange(formatEndpoint(endpoint, queryParams), HttpMethod.GET, new HttpEntity<>(headers), Object.class);
    }

    public <T> T get(String endpoint, Map<String, List<String>> queryParams, Class<T> responseType) {
        return restTemplate.exchange(formatEndpoint(endpoint, queryParams), HttpMethod.GET, new HttpEntity<>(headers), responseType).getBody();
    }

    public <T> T get(String endpoint, Map<String, List<String>> queryParams, ParameterizedTypeReference<T> responseType) {
        return restTemplate.exchange(formatEndpoint(endpoint, queryParams), HttpMethod.GET, new HttpEntity<>(headers), responseType).getBody();
    }

    public void post(String endpoint, Object body) {
        HttpEntity<?> httpEntity = body != null ? new HttpEntity<>(body, headers) : new HttpEntity<>(headers);
        restTemplate.exchange(formatEndpoint(endpoint), HttpMethod.POST, httpEntity, Object.class);
    }

    public <T> ResponseEntity<T> post(String endpoint, Object body, ParameterizedTypeReference<T> responseType) {
        HttpEntity<?> httpEntity = body != null ? new HttpEntity<>(body, headers) : new HttpEntity<>(headers);
        return restTemplate.exchange(formatEndpoint(endpoint), HttpMethod.POST, httpEntity, responseType);
    }

    public <T> ResponseEntity<T> post(String endpoint, Object body, Class<T> responseType) {
        HttpEntity<?> httpEntity = body != null ? new HttpEntity<>(body, headers) : new HttpEntity<>(headers);
        return restTemplate.exchange(formatEndpoint(endpoint), HttpMethod.POST, httpEntity, responseType);
    }

    public <T> ResponseEntity<T> post(String endpoint, Map<String, List<String>> queryParams, Object body, Class<T> responseType) {
        HttpEntity<?> httpEntity = body != null ? new HttpEntity<>(body, headers) : new HttpEntity<>(headers);
        return restTemplate.exchange(formatEndpoint(endpoint, queryParams), HttpMethod.POST, httpEntity, responseType);
    }

    public void put(String endpoint, Object body) {
        HttpEntity<?> httpEntity = body != null ? new HttpEntity<>(body, headers) : new HttpEntity<>(headers);
        restTemplate.exchange(formatEndpoint(endpoint), HttpMethod.PUT, httpEntity, Object.class);
    }

    public <T> ResponseEntity<T> put(String endpoint, Object body, Class<T> responseType) {
        HttpEntity<?> httpEntity = body != null ? new HttpEntity<>(body, headers) : new HttpEntity<>(headers);
        return restTemplate.exchange(formatEndpoint(endpoint), HttpMethod.PUT, httpEntity, responseType);
    }

    public <T> T put(String endpoint, Object body, ParameterizedTypeReference<T> responseType) {
        HttpEntity<?> httpEntity = body != null ? new HttpEntity<>(body, headers) : new HttpEntity<>(headers);
        return restTemplate.exchange(formatEndpoint(endpoint), HttpMethod.PUT, httpEntity, responseType).getBody();
    }

    public <T> T put(String endpoint, Map<String, List<String>> queryParams, Object body, ParameterizedTypeReference<T> responseType) {
        HttpEntity<?> httpEntity = body != null ? new HttpEntity<>(body, headers) : new HttpEntity<>(headers);
        return restTemplate.exchange(formatEndpoint(endpoint, queryParams), HttpMethod.PUT, httpEntity, responseType).getBody();
    }

    public <T> ResponseEntity<T> patch(String endpoint, Object body, Class<T> responseType) {
        HttpEntity<?> httpEntity = body != null ? new HttpEntity<>(body, headers) : new HttpEntity<>(headers);
        return restTemplate.exchange(formatEndpoint(endpoint), HttpMethod.PATCH, httpEntity, responseType);
    }

    public <T> ResponseEntity<T> patch(String endpoint, Map<String, List<String>> queryParams, Object body, Class<T> responseType) {
        HttpEntity<?> httpEntity = body != null ? new HttpEntity<>(body, headers) : new HttpEntity<>(headers);
        return restTemplate.exchange(formatEndpoint(endpoint, queryParams), HttpMethod.PATCH, httpEntity, responseType);
    }

    public <T> ResponseEntity<T> patch(String endpoint, Map<String, List<String>> queryParams, Object body, ParameterizedTypeReference<T> responseType) {
        HttpEntity<?> httpEntity = body != null ? new HttpEntity<>(body, headers) : new HttpEntity<>(headers);
        return restTemplate.exchange(formatEndpoint(endpoint, queryParams), HttpMethod.PATCH, httpEntity, responseType);
    }

    public <T> T patch(String endpoint, Object body, ParameterizedTypeReference<T> responseType) {
        HttpEntity<?> httpEntity = body != null ? new HttpEntity<>(body, headers) : new HttpEntity<>(headers);
        return restTemplate.exchange(formatEndpoint(endpoint), HttpMethod.PATCH, httpEntity, responseType).getBody();
    }

    private String formatEndpoint(String rawEndpoint) {
        if (rawEndpoint.charAt(0) != '/')
            rawEndpoint = "/" + rawEndpoint;

        return ip + rawEndpoint;
    }

    private String formatEndpoint(String rawEndpoint, Map<String, List<String>> queryParams) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(formatEndpoint(rawEndpoint));
        for (Map.Entry<String, List<String>> param : queryParams.entrySet()) {
            for (String paramList : param.getValue()) {
                try {
                    paramList = URLEncoder.encode(paramList, StandardCharsets.UTF_8.name());
                } catch (UnsupportedEncodingException e) {

                }
                uriComponentsBuilder.queryParam(param.getKey(), paramList);
            }
        }
        return uriComponentsBuilder.build().toString();
    }

}

