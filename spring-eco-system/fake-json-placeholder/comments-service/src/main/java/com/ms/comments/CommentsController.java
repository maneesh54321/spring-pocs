package com.ms.comments;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
public class CommentsController {

	@GetMapping("")
	public List<Comment> getAllComments(){
		return List.of(new Comment(
				1, 1, "Dummy Comment", "Dummy email", "Dummy body"
		));
	}
}
