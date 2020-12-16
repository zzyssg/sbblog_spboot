package com.zzy.sbblog.controller;

import com.zzy.sbblog.entity.Comment;
import com.zzy.sbblog.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zzy
 * @Date 2020/12/15 16:40
 */
@RestController
@RequestMapping("/comment")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/commit")
    public Boolean commit(@RequestBody Comment comment) {
        log.info(comment.getContent());
        //假如userId为-1，则前端是未登录的
        if (comment.getUserId() == null || comment.getUserId().equals(-1)) {
            return false;
//            throw new IllegalArgumentException("前端未登录");
        }
        try {
            Integer effected = commentService.addComment(comment);
        } catch (Exception e) {
            log.error("新增评论时出错:{}", e.getMessage());
        }
        return true;
    }


}
