package com.example.lkplaces.service;

import com.example.lkplaces.jpa.entity.Event;
import com.example.lkplaces.jpa.enums.EnumStatus;

import java.util.List;

public interface EventService {
    Event add(Event post);

    Event update(Event post);

    void delete(Integer id);

    Event getById(Integer id);

    List<Event> getAll();

    Event changeStatus(Integer id, EnumStatus status);
}
