package com.zzy.sbblog.service;

import com.zzy.sbblog.entity.User;

/**
 * @author zzy
 * @Date 2020/10/27 17:33
 */
public interface UserService {

    public User getUserByUsername(String username);

}
