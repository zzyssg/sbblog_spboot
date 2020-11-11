package com.zzy.sbblog.service.impl;

import com.zzy.sbblog.dao.UserMapper;
import com.zzy.sbblog.entity.User;
import com.zzy.sbblog.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author zzy
 * @Date 2020/10/27 17:33
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User getUserByUsername(String username) {
        User user = userMapper.getUserByUsername(username);
        return user;
    }
}
