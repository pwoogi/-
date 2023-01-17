package com.dashboard.projectboard.controller;

import com.dashboard.projectboard.controller.request.PostCreateRequest;
import com.dashboard.projectboard.controller.request.PostModifyRequest;
import com.dashboard.projectboard.controller.response.PostResponse;
import com.dashboard.projectboard.controller.response.Response;
import com.dashboard.projectboard.model.Post;
import com.dashboard.projectboard.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public Response<Void> create(@RequestBody PostCreateRequest request, Authentication authentication){
        postService.create(request.getTitle(), request.getBody(), authentication.getName());
        return Response.success();

    }

    @PutMapping("/{postId}")
    public Response<PostResponse> modify(@PathVariable Integer postId, @RequestBody PostModifyRequest request, Authentication authentication){
        Post post = postService.modify(request.getTitle(), request.getBody(), authentication.getName(), postId);
        return Response.success(PostResponse.fromPost(post));

    }
}
