package com.example.restclient.controller;

import com.example.restclient.Post;
import com.example.restclient.PostClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {

    private final PostClient postClient;

    public PostController(PostClient postClient) {
        this.postClient = postClient;
    }

    @GetMapping("/posts")
    public List<Post> getAllPosts(){
        return this.postClient.findAll();
    }
}
