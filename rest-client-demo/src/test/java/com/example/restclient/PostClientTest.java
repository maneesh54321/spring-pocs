package com.example.restclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;

import java.util.List;

@RestClientTest(PostClient.class)
class PostClientTest {

    @Autowired
    private MockRestServiceServer restServiceServer;

    @Autowired
    private PostClient postClient;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldFindAllPosts() throws JsonProcessingException {
        // given
        List<Post> posts = List.of(
                new Post(1, 1, "Dummy Title", "Dummy Body"),
                new Post(1, 1, "Dummy Title 2", "Dummy Body 2")
        );
        // when
        restServiceServer
                .expect(MockRestRequestMatchers.requestTo("https://jsonplaceholder.typicode.com/posts"))
                .andRespond(MockRestResponseCreators.withSuccess(objectMapper.writeValueAsString(posts), MediaType.APPLICATION_JSON));
        // then

        Assertions.assertEquals(2, postClient.findAll().size());
    }
}