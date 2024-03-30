package com.nexus.nexus.controllers;

import com.nexus.nexus.models.Content;
import com.nexus.nexus.repositories.ContentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/content")
public class ContentController {

    private final ContentRepository repository;


    public ContentController(ContentRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Content> findAll() {
        return repository.findAll();
    }

    @GetMapping(params = "id")
    public Content findByID(@RequestParam Integer id) {

        return repository.findByID(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found"));
    }

    @GetMapping(params = "title")
    public Content findByTitle(@RequestParam String title) {
        return repository.findByTitle(title).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found"));
    }

    @GetMapping(params = {"id", "title"})
    public Content findByIDandTitle(@RequestParam Integer id, @RequestParam String title) {
        return repository.findByIDandTitle(id,title).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found"));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@RequestBody Content content) {
        repository.createContent(content);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(params = "id")
    public void update(@RequestBody Content content, @RequestParam Integer id) {
        if(!repository.existsById(id)) {throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found"); }
        repository.createContent(content);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(params = "id")
    public void delete(@RequestParam Integer id) {
        repository.deleteContent(id);
    }
}

