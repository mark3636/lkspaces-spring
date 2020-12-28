package com.example.lkplaces.controller;

import com.example.lkplaces.jpa.entity.Event;
import com.example.lkplaces.jpa.enums.EnumStatus;
import com.example.lkplaces.service.EventService;
import com.example.lkplaces.web.dto.EventDto;
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
@RequestMapping("/api/events")
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<Event> getAll() {
        return eventService.getAll();
    }

    @GetMapping("/{id}")
    public Event getById(@PathVariable Integer id) {
        return eventService.getById(id);
    }

    @PostMapping
    public Event add(@RequestBody EventDto event) {
        return eventService.add(event);
    }

    @PutMapping("/{id}")
    public Event update(@RequestBody EventDto event) {
        return eventService.update(event);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        eventService.delete(id);
    }

    @PostMapping("/{id}")
    public Event changeStatus(@PathVariable Integer id, @RequestParam("status") EnumStatus status) {
        return eventService.changeStatus(id, status);
    }
}
