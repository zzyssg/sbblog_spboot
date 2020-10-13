package com.zzy.sbblog.dao;

import com.zzy.sbblog.entity.Blog;
import com.zzy.sbblog.entity.Type;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TypeMapper {

    Type queryTypeByTypeId(Long typeId);
//    List<Blog> queryTypeByTypeId(Long typeId);

    List<Type> queryAllTypes();

    Integer addType(Type type);

    Integer deleteTypeByTypeId(Long typeId);

}
