package com.example.lkplaces.service;

import com.example.lkplaces.jpa.entity.Event;
import com.example.lkplaces.jpa.enums.EnumStatus;
import com.example.lkplaces.web.dto.EventDto;

import java.util.List;

public interface EventService {
    Event add(EventDto post);

    Event update(EventDto post);

    void delete(Integer id);

    Event getById(Integer id);

    List<Event> getAll();

    Event changeStatus(Integer id, EnumStatus status);
}
