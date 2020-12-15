package com.zzy.sbblog.entity;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 博客类型
 * @author zzy
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Type {
    /**
     * 类型ID
     */
    private Long id;
    /**
     * 类型名称
     */
    private String name;
    /**
     * 类型下博客集合
     */
    private List<Blog> blogs = new ArrayList<>();

}
