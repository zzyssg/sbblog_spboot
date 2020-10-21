package com.zzy.sbblog.service.impl;

import com.zzy.sbblog.dao.UserMapper;
import com.zzy.sbblog.entity.User;
import com.zzy.sbblog.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User getUserByUsername(String username) {
        User user = userMapper.getUserByUsername(username);
        return user;
    }
}
