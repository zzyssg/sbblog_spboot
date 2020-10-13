package com.zzy.sbblog.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class Blog {

    private Long id;
    private String title;
    private String content;
    private String firstPicture;
    private String description;
    private String flag;
    private String views;
    private boolean appreciation;
    private boolean sharement;
    private boolean commentabled;
    private boolean published;
    private boolean recommend;
    private Date createTime;
    private Date updateTime;


    private Integer userId;
    private User user;

    private Integer typeId;
    private Type type;


//    private List<Tag> tags = new ArrayList<>();
//    private List<Comment> comments = new ArrayList<>();
//    private User user ;


    public Blog() {
    }

    public Blog(Long id, String title, String content, String firstPicture, String description, String flag, String views, boolean appreciation, boolean sharement, boolean commentabled, boolean published, boolean recommend, Date createTime, Date updateTime, Integer typeId, User user, Integer user_id, Type type) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.firstPicture = firstPicture;
        this.description = description;
        this.flag = flag;
        this.views = views;
        this.appreciation = appreciation;
        this.sharement = sharement;
        this.commentabled = commentabled;
        this.published = published;
        this.recommend = recommend;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.typeId = typeId;
        this.user = user;
        this.userId = userId;
        this.type = type;
    }
}
