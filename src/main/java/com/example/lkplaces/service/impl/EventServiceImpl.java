package com.example.lkplaces.service.impl;

import com.example.lkplaces.jpa.entity.Event;
import com.example.lkplaces.jpa.enums.EnumStatus;
import com.example.lkplaces.jpa.repository.EventRepository;
import com.example.lkplaces.security.CurrentUserProvider;
import com.example.lkplaces.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final CurrentUserProvider currentUserProvider;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository,
                            CurrentUserProvider currentUserProvider) {
        this.eventRepository = eventRepository;
        this.currentUserProvider = currentUserProvider;
    }

    @Override
    public Event add(Event event) {
        event.setAuthor(currentUserProvider.getCurrentUser());
        return eventRepository.save(event);
    }

    @Override
    public Event update(Event post) {
        return eventRepository.save(post);
    }

    @Override
    public void delete(Integer id) {
        eventRepository.deleteById(id);
    }

    @Override
    public Event getById(Integer id) {
        return eventRepository.findById(id).orElse(null);
    }

    @Override
    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    @Override
    public Event changeStatus(Integer id, EnumStatus status) {
        Event post = eventRepository.findById(id).orElseThrow(() -> new RuntimeException("Событие не найдено"));
        post.setStatus(status);
        return eventRepository.save(post);
    }
}
