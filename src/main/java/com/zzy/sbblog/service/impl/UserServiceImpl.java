package com.zzy.sbblog.service.impl;

import com.zzy.sbblog.dao.UserMapper;
import com.zzy.sbblog.entity.User;
import com.zzy.sbblog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zzy
 * @Date 2020/12/15 10:08
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    /**
     * 根据用户ID查询用户
     *
     * @param userId
     * @return
     */
    @Override
    public User getUserById(Integer userId) {
        return userMapper.getUserById(userId);
    }

    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    @Override
    public User queryUserByName(String username) {
        return userMapper.getUserByName(username);
    }

}
