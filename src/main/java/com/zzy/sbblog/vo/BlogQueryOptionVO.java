package com.zzy.sbblog.vo;

import com.zzy.sbblog.entity.Type;
import lombok.Data;
import lombok.ToString;

/**
 * @author zzy
 * @Date 2020/10/27 17:33
 */
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
