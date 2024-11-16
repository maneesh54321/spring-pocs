package com.example.restclient.controller;

import com.example.restclient.Post;
import com.example.restclient.ReqResLoggingInterceptor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController
public class PostController {

    private final RestClient restClient;

    public PostController(RestClient.Builder builder) {
        ClientHttpRequestFactory requestFactory = new BufferingClientHttpRequestFactory(new JdkClientHttpRequestFactory());
        this.restClient = builder
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .requestInterceptor(new ReqResLoggingInterceptor())
                .requestFactory(requestFactory)
                .build();
    }

    @GetMapping("/posts")
    public List<Post> getAllPosts(){
        return this.restClient.get()
                .uri("/posts")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }
}
