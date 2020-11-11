package com.zzy.sbblog.entity;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zzy
 * @Date 2020/10/27 17:33
 */
@Data
@ToString
public class Comment {

    private Long id;
    private String nickname;
    private String email;
    private String content;
    private String avatar;
    private Date createTime;

    private Blog blog;

    private List<Comment> replyComments = new ArrayList<>();

    private Comment parentComment;

    public Comment() {
    }

    public Comment(Long id, String nickname, String email, String content, String avatar, Date createTime, Blog blog, List<Comment> replyComments, Comment parentComment) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.content = content;
        this.avatar = avatar;
        this.createTime = createTime;
        this.blog = blog;
        this.replyComments = replyComments;
        this.parentComment = parentComment;
    }
}
