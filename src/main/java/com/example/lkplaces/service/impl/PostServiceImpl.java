package com.example.lkplaces.service.impl;

import com.example.lkplaces.jpa.entity.MapMarker;
import com.example.lkplaces.jpa.entity.PlaceType;
import com.example.lkplaces.jpa.entity.Post;
import com.example.lkplaces.jpa.enums.EnumActionType;
import com.example.lkplaces.jpa.enums.EnumDomainType;
import com.example.lkplaces.jpa.enums.EnumStatus;
import com.example.lkplaces.jpa.repository.PostRepository;
import com.example.lkplaces.security.CurrentUserProvider;
import com.example.lkplaces.service.AuditService;
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
    private final AuditService auditService;

    @Autowired
    public PostServiceImpl(PostRepository postRepository,
                           CurrentUserProvider currentUserProvider,
                           PlaceTypeService placeTypeService,
                           MapMarkerService mapMarkerService,
                           AuditService auditService) {
        this.postRepository = postRepository;
        this.currentUserProvider = currentUserProvider;
        this.placeTypeService = placeTypeService;
        this.mapMarkerService = mapMarkerService;
        this.auditService = auditService;
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
        newPost = postRepository.save(newPost);
        auditService.audit(EnumActionType.CREATE, EnumDomainType.POST);
        return newPost;
    }

    @Override
    public Post update(PostDto post) {
        PlaceType placeType = placeTypeService.getById(post.getPlaceTypeId());
        MapMarker mapMarker = post.getMapMarkerId() == null ? null : mapMarkerService.getById(post.getMapMarkerId());
        Post oldPost = getById(post.getId());
        oldPost.setLabel(post.getLabel());
        oldPost.setDescription(post.getDescription());
        oldPost.setPlaceType(placeType);
        oldPost.setMapMarker(mapMarker);
        oldPost = postRepository.save(oldPost);
        auditService.audit(EnumActionType.UPDATE, EnumDomainType.POST);
        return oldPost;
    }

    @Override
    public void delete(Integer id) {
        postRepository.deleteById(id);
        auditService.audit(EnumActionType.DELETE, EnumDomainType.POST);
    }

    @Override
    public Post getById(Integer id) {
        return postRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Пост не найден"));
    }

    @Override
    public List<Post> getAll() {
        return postRepository.findAll();
    }

    @Override
    public Post changeStatus(Integer id, EnumStatus status) {
        Post post = getById(id);
        post.setStatus(status);
        post = postRepository.save(post);
        auditService.audit(EnumStatus.APPROVED.equals(status) ? EnumActionType.APPROVE : EnumActionType.REJECT,
                EnumDomainType.POST);
        return post;
    }
}
