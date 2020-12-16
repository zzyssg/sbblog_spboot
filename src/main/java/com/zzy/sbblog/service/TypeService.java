package com.zzy.sbblog.service;

import com.zzy.sbblog.entity.Type;

import java.util.List;

/**
 * @author zzy
 * @Date 2020/10/27 17:33
 */
public interface TypeService {

    Type queryTypeByTypeId(Long typeId);

    Type queryTypeByName(String typeName);
//    List<Blog> queryTypeByTypeId(Long typeId);

    List<Type> queryAllTypes();

    Type addType(Type type);

    Integer deleteTypeByTypeId(Long typeId);

}
