package com.zzy.sbblog.entity;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zzy
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {

    /**
     * 评论ID
     */
    private Integer commentId;
    /**
     * 评论所属用户
     */
    private Integer userId;
    private User user;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 评论时间
     */
    private Date createTime;

    /**
     * 评论所属博客
     */
    private Integer blogId;

    /**
     * 评论的子评论
     */
    private List<Comment> replyComments = new ArrayList<>();

//    /**
//     * 评论的上级
//     */
//    private Comment parentComment;

    /**
     * 父级评论的用户
     */
    private User parentCommentUser;

    /**
     * 父评论ID
     */
    private Integer parentCommentId;
}
