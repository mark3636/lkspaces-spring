package com.example.lkplaces.controller;

import com.example.lkplaces.jpa.entity.Post;
import com.example.lkplaces.jpa.enums.EnumStatus;
import com.example.lkplaces.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getAll() {
        return postService.getAll();
    }

    @GetMapping("/{id}")
    public Post getById(@PathVariable Integer id) {
        return postService.getById(id);
    }

    @PostMapping
    public Post add(@RequestBody Post post) {
        return postService.add(post);
    }

    @PutMapping("/{id}")
    public Post update(@RequestBody Post post) {
        return postService.update(post);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        postService.delete(id);
    }

    @PostMapping("/{id}")
    public Post changeStatus(@PathVariable Integer id, @RequestParam("status") EnumStatus status) {
        return postService.changeStatus(id, status);
    }
}
