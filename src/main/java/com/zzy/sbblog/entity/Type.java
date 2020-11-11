package com.zzy.sbblog.entity;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzy
 * @Date 2020/10/27 17:33
 */
@Data
@ToString
public class Type {

    private Long id;
    private String name;

    private List<Blog> blogs = new ArrayList<>();

    public Type() {
    }

    public Type(Long id, String name, List<Blog> blogs) {
        this.id = id;
        this.name = name;
        this.blogs = blogs;
    }
}
