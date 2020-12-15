package com.zzy.sbblog.service.impl;

import com.zzy.sbblog.dao.CommentMapper;
import com.zzy.sbblog.entity.Comment;
import com.zzy.sbblog.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zzy
 * @Date 2020/12/15 16:45
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;

    /**
     * 新增评论
     *
     * @param comment
     * @return
     */
    @Override
    public Integer addComment(Comment comment) {
        return commentMapper.insertComment(comment);
    }

}
