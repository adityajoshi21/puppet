package com.blucharge.puppet.ocpi.controller;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class RestClientParams<T> {
    private String url;
    private HttpMethod httpMethod;
    private HttpHeaders httpHeaders;
    private Object payload;
    private Class<T> responseTypeClass;
    private ParameterizedTypeReference<T> responseTypeRef;
    private Boolean failFast;
    private Boolean isAuthenticated;
    private Boolean retry;
    private List<Object> uriVariables;

    private RestTemplate restTemplate;
    private Boolean disableResponseLogs;
    private String token;
}