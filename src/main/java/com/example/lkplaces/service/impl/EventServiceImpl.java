package com.example.lkplaces.service.impl;

import com.example.lkplaces.jpa.entity.Event;
import com.example.lkplaces.jpa.entity.MapMarker;
import com.example.lkplaces.jpa.enums.EnumStatus;
import com.example.lkplaces.jpa.repository.EventRepository;
import com.example.lkplaces.security.CurrentUserProvider;
import com.example.lkplaces.service.EventService;
import com.example.lkplaces.service.MapMarkerService;
import com.example.lkplaces.web.dto.EventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final CurrentUserProvider currentUserProvider;
    private final MapMarkerService mapMarkerService;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository,
                            CurrentUserProvider currentUserProvider,
                            MapMarkerService mapMarkerService) {
        this.eventRepository = eventRepository;
        this.currentUserProvider = currentUserProvider;
        this.mapMarkerService = mapMarkerService;
    }

    @Override
    public Event add(EventDto event) {
        MapMarker mapMarker = event.getMapMarkerId() == null ? null : mapMarkerService.getById(event.getMapMarkerId());
        Event newEvent = Event.builder()
                .label(event.getLabel())
                .description(event.getDescription())
                // TODO: add DateFormatter
                .startDate(LocalDateTime.parse(event.getStartDate()))
                .endDate(LocalDateTime.parse(event.getEndDate()))
                .status(EnumStatus.WAITING_FOR_APPROVAL)
                .author(currentUserProvider.getCurrentUser())
                .mapMarker(mapMarker)
                .build();
        return eventRepository.save(newEvent);
    }

    @Override
    public Event update(EventDto event) {
        MapMarker mapMarker = event.getMapMarkerId() == null ? null : mapMarkerService.getById(event.getMapMarkerId());
        Event newEvent = Event.builder()
                .id(event.getId())
                .label(event.getLabel())
                .description(event.getDescription())
                // TODO: add DateFormatter
                .startDate(LocalDateTime.parse(event.getStartDate()))
                .endDate(LocalDateTime.parse(event.getEndDate()))
                .mapMarker(mapMarker)
                .build();
        return eventRepository.save(newEvent);
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
        Event event = eventRepository.findById(id).orElseThrow(() -> new RuntimeException("Событие не найдено"));
        event.setStatus(status);
        return eventRepository.save(event);
    }
}
