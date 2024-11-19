package com.example.restclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class PostClient {

    private final String baseUrl;

    private final RestClient restClient;

    public PostClient(@Value("${JSON_PLACEHOLDER_BASE_URL}") String baseUrl, RestClient.Builder builder) {
        this.baseUrl = baseUrl;

//        var requestFactory = new BufferingClientHttpRequestFactory(new JdkClientHttpRequestFactory());
        this.restClient = builder
                .baseUrl(this.baseUrl)
//                .requestInterceptor(new ReqResLoggingInterceptor())
//                .requestFactory(requestFactory)
                .build();
    }

    public List<Post> findAll() {
        return this.restClient.get().uri("/posts").retrieve().body(new ParameterizedTypeReference<List<Post>>() {
        });
    }
}
