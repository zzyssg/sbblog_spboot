package com.zzy.sbblog.dao;

import com.zzy.sbblog.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    public User getUserByUsername(String username);

}
