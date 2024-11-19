package com.ms.posts;

import java.util.List;

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
	private final RestClient restClient;

	public PostsController(DiscoveryClient discoveryClient, RestClient.Builder builder) {
		ServiceInstance serviceInstance = discoveryClient.getInstances("comments-service").get(0);
		this.restClient = builder
				.baseUrl(serviceInstance.getUri().toString())
				.build();
	}

	@GetMapping("")
	public List<Post> getAllPosts(){
		return List.of(new Post(1, 1, "dummy post title", "dummy post body"));
	}

	@GetMapping("/comments")
	public List<Comment> getAllComments() {
		return restClient.get()
				.uri("/comments")
				.retrieve()
				.body(new ParameterizedTypeReference<List<Comment>>() {
				});
	}
}
