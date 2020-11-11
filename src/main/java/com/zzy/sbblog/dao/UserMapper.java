package com.zzy.sbblog.dao;

import com.zzy.sbblog.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zzy
 * @Date 2020/10/27 17:33
 */
@Mapper
public interface UserMapper {

    public User getUserByUsername(String username);

}
