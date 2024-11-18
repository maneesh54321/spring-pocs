package com.ms.posts;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
public class PostsController {

	@GetMapping("")
	public List<Post> getAllPosts(){
		return List.of(new Post(1, 1, "dummy post title", "dummy post body"));
	}
}
