package org.example.model.entity;

import lombok.Data;

@Data
public class Post {
    private Long userId;
    private Long id;
    private String title;
    private String body;
}
