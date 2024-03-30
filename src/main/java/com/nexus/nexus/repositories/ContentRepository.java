package com.nexus.nexus.repositories;

import com.nexus.nexus.models.Content;
import com.nexus.nexus.models.Status;
import com.nexus.nexus.models.Type;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ContentRepository {
    private final List<Content> contentList = new ArrayList<>();

    public ContentRepository(){}

    public List<Content> findAll() {
        return contentList;
    }

    public Optional<Content> findByID(Integer id) {
        return contentList.stream().filter(content -> content.id().equals(id)).findFirst();
    }

    public Optional<Content> findByTitle(String title) {
        return contentList.stream().filter(content -> content.title().equals(title)).findFirst();
    }

    public Optional<Content> findByIDandTitle(Integer id, String title) {
        return contentList.stream().filter(content -> content.id().equals(id) && content.title().equals(title)).findFirst();
    }

    public void createContent(Content content) {
        contentList.removeIf(c -> c.id().equals(content.id()));
        contentList.add(content);
    }

    public void deleteContent(Integer id) {
        contentList.removeIf(c -> c.id().equals(id));
    }

    public boolean existsById(Integer id) {
        return contentList.stream().filter(content -> content.id().equals(id)).count() == 1;
    }


    @PostConstruct
    private void init() {
        Content content = new Content(1,
                "JS-Frameworks",
                "React app for keeping track of JS frameworks",
                Status.PUBLISHED,
                Type.WEB_APP,
                LocalDateTime.now(),
                LocalDateTime.now(),
                "");
        contentList.add(content);
    }
}
