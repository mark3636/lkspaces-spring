package com.example.lkplaces.service.impl;

import com.example.lkplaces.jpa.entity.MapMarker;
import com.example.lkplaces.jpa.entity.PlaceType;
import com.example.lkplaces.jpa.entity.Post;
import com.example.lkplaces.jpa.enums.EnumStatus;
import com.example.lkplaces.jpa.repository.PostRepository;
import com.example.lkplaces.security.CurrentUserProvider;
import com.example.lkplaces.service.MapMarkerService;
import com.example.lkplaces.service.PlaceTypeService;
import com.example.lkplaces.service.PostService;
import com.example.lkplaces.web.dto.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final CurrentUserProvider currentUserProvider;
    private final PlaceTypeService placeTypeService;
    private final MapMarkerService mapMarkerService;

    @Autowired
    public PostServiceImpl(PostRepository postRepository,
                           CurrentUserProvider currentUserProvider,
                           PlaceTypeService placeTypeService,
                           MapMarkerService mapMarkerService) {
        this.postRepository = postRepository;
        this.currentUserProvider = currentUserProvider;
        this.placeTypeService = placeTypeService;
        this.mapMarkerService = mapMarkerService;
    }

    @Override
    public Post add(PostDto post) {
        PlaceType placeType = placeTypeService.getById(post.getPlaceTypeId());
        MapMarker mapMarker = post.getMapMarkerId() == null ? null : mapMarkerService.getById(post.getMapMarkerId());
        Post newPost = Post.builder()
                .label(post.getLabel())
                .description(post.getDescription())
                .date(LocalDateTime.now())
                .status(EnumStatus.WAITING_FOR_APPROVAL)
                .placeType(placeType)
                .mapMarker(mapMarker)
                .author(currentUserProvider.getCurrentUser())
                .build();
        return postRepository.save(newPost);
    }

    @Override
    public Post update(PostDto post) {
        PlaceType placeType = placeTypeService.getById(post.getPlaceTypeId());
        MapMarker mapMarker = post.getMapMarkerId() == null ? null : mapMarkerService.getById(post.getMapMarkerId());
        Post newPost = Post.builder()
                .id(post.getId())
                .label(post.getLabel())
                .description(post.getDescription())
                .placeType(placeType)
                .mapMarker(mapMarker)
                .build();
        return postRepository.save(newPost);
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
