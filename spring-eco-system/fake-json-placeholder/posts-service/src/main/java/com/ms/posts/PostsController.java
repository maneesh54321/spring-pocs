package com.ms.posts;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("/posts")
public class PostsController {
    private static final Logger log = LoggerFactory.getLogger(PostsController.class);
    private final RestClient restClient;
    private final DiscoveryClient discoveryClient;

    public PostsController(RestClient.Builder builder, DiscoveryClient discoveryClient1) {
        this.restClient = builder
                .build();
        this.discoveryClient = discoveryClient1;
    }

    @GetMapping("")
    public List<Post> getAllPosts() {
        log.info("Fetching posts..");
        return List.of(new Post(1, 1, "dummy post title", "dummy post body"));
    }

    @GetMapping("/comments")
    public List<Comment> getAllComments() {
        ServiceInstance serviceInstance = discoveryClient.getInstances("comments-service").get(0);

        return restClient.get()
                .uri(serviceInstance.getUri()+ "/comments")
                .retrieve()
                .body(new ParameterizedTypeReference<List<Comment>>() {
                });
    }
}
