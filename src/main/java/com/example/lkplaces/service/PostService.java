package com.example.lkplaces.service;

import com.example.lkplaces.jpa.entity.Post;
import com.example.lkplaces.jpa.enums.EnumStatus;
import com.example.lkplaces.web.dto.PostDto;

import java.util.List;

public interface PostService {
    Post add(PostDto post);

    Post update(PostDto post);

    void delete(Integer id);

    Post getById(Integer id);

    List<Post> getAll();

    Post changeStatus(Integer id, EnumStatus status);
}
