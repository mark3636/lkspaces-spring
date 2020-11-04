package com.example.lkplaces.service;

import com.example.lkplaces.jpa.entity.Post;
import com.example.lkplaces.jpa.entity.User;
import com.example.lkplaces.jpa.enums.EnumStatus;

import java.util.List;

public interface PostService {
    Post add(Post post);

    Post update(Post post);

    void delete(Integer id);

    Post getById(Integer id);

    List<Post> getAll();

    Post changeStatus(Integer id, EnumStatus status);
}
