package com.example.lkplaces.service.impl;

import com.example.lkplaces.jpa.entity.Post;
import com.example.lkplaces.jpa.enums.EnumStatus;
import com.example.lkplaces.jpa.repository.PostRepository;
import com.example.lkplaces.security.CurrentUserProvider;
import com.example.lkplaces.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final CurrentUserProvider currentUserProvider;

    @Autowired
    public PostServiceImpl(PostRepository postRepository,
                           CurrentUserProvider currentUserProvider) {
        this.postRepository = postRepository;
        this.currentUserProvider = currentUserProvider;
    }

    @Override
    public Post add(Post post) {
        post.setDate(LocalDateTime.now());
        post.setAuthor(currentUserProvider.getCurrentUser());
        return postRepository.save(post);
    }

    @Override
    public Post update(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void delete(Integer id) {
        postRepository.deleteById(id);
    }

    @Override
    public Post getById(Integer id) {
        return postRepository.findById(id).orElse(null);
    }

    @Override
    public List<Post> getAll() {
        return postRepository.findAll();
    }

    @Override
    public Post changeStatus(Integer id, EnumStatus status) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Пост не найден"));
        post.setStatus(status);
        return postRepository.save(post);
    }
}
