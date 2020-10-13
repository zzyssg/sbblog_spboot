package com.zzy.sbblog.service;

import com.zzy.sbblog.entity.Blog;
import com.zzy.sbblog.entity.Type;

import java.util.List;

public interface TypeService {

    Type queryTypeByTypeId(Long typeId);
//    List<Blog> queryTypeByTypeId(Long typeId);

    List<Type> queryAllTypes();

    Type addType(Type type);

    Integer deleteTypeByTypeId(Long typeId);

}
