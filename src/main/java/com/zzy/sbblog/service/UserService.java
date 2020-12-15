package com.zzy.sbblog.service;

import com.zzy.sbblog.entity.User;

/**
 * @author zzy
 * @Date 2020/12/15 10:07
 */
public interface UserService {
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
    User queryUserByName(String username);
}
