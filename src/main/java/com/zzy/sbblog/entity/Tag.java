package com.zzy.sbblog.entity;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
public class Tag {

    private Long id;
    private String name;

    private List<Blog> blogs = new ArrayList<>();

    public Tag() {
    }

    public Tag(Long id, String name, List<Blog> blogs) {
        this.id = id;
        this.name = name;
        this.blogs = blogs;
    }
}
