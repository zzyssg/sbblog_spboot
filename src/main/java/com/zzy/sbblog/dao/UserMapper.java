package com.zzy.sbblog.dao;

import com.zzy.sbblog.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zzy
 * @Date 2020/12/15 9:38
 */
@Mapper
public interface UserMapper {
    /**
     * 根据用户ID查询用户
     * @param userId
     * @return
     */
    User getUserById(Integer userId);

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User getUserByName(String username);

}
