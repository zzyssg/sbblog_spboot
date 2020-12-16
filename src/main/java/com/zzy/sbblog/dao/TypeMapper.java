package com.zzy.sbblog.dao;

import com.zzy.sbblog.entity.Blog;
import com.zzy.sbblog.entity.Type;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author zzy
 * @Date 2020/10/27 17:33
 */
@Mapper
public interface TypeMapper {

    Type queryTypeByTypeId(Long typeId);
//    List<Blog> queryTypeByTypeId(Long typeId);

    Type queryTypeByName(String typeName);

    List<Type> queryAllTypes();

    Integer addType(Type type);

    Integer deleteTypeByTypeId(Long typeId);

}
