package com.example.restclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class ReqResLoggingInterceptor implements ClientHttpRequestInterceptor {
    public static final Logger log = LoggerFactory.getLogger(ReqResLoggingInterceptor.class);
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        log.info(new String(body));
        ClientHttpResponse clientHttpResponse = execution.execute(request, body);
        log.info(new String(clientHttpResponse.getBody().readAllBytes()));
        return clientHttpResponse;
    }
}
