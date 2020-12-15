package com.zzy.sbblog.service;

import com.zzy.sbblog.entity.Comment;

/**
 * @author zzy
 * @Date 2020/12/15 16:44
 */
public interface CommentService {
    /**
     * 新增评论
     * @param comment
     * @return
     */
    Integer addComment(Comment comment);
}
