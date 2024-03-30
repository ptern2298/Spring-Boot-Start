package com.nexus.nexus.models;

import java.time.LocalDateTime;

public record Content (
        Integer id,
        String title,
        String description,
        Status status,
        Type type,
        LocalDateTime createTime,
        LocalDateTime updateTime,
        String url
) {


}
