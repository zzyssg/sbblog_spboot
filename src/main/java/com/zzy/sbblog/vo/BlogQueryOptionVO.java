package com.zzy.sbblog.vo;

import com.zzy.sbblog.entity.Type;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BlogQueryOptionVO {

    private String name;
    private boolean recommend;
    private Type type;

    public BlogQueryOptionVO() {
    }

    public BlogQueryOptionVO(String name, boolean recommend, Type type) {
        this.name = name;
        this.recommend = recommend;
        this.type = type;
    }
}
