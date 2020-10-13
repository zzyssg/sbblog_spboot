package com.zzy.sbblog.vo;

import com.zzy.sbblog.entity.Type;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class BlogVO {
    private Long id;
    private String title;
    private String content;
    private String firstPicture;
    private String description;
    private boolean sharement;
    private boolean recommend;
    private Date updateTime;
    private Long typeId;
    private Type type;

    public BlogVO() {
    }

    public BlogVO(Long id, String title, String content, String firstPicture, boolean sharement, String description, boolean recommend, Date updateTime, Long typeId, Type type) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.firstPicture = firstPicture;
        this.sharement = sharement;
        this.description = description;
        this.recommend = recommend;
        this.updateTime = updateTime;
        this.typeId = typeId;
        this.type = type;
    }
}
