package com.zzy.sbblog.dao;

import com.zzy.sbblog.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zzy
 * @Date 2020/12/15 17:01
 */
@Mapper
public interface CommentMapper {

    /**
     * 新增评论
     * @param comment
     * @return
     */
    Integer insertComment(Comment comment);

}
