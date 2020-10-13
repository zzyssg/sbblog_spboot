package com.zzy.sbblog.service.impl;

import com.zzy.sbblog.dao.TypeMapper;
import com.zzy.sbblog.entity.Blog;
import com.zzy.sbblog.entity.Type;
import com.zzy.sbblog.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Resource
    private TypeMapper typeMapper;

    @Override
    public Type queryTypeByTypeId(Long typeId) {
        return typeMapper.queryTypeByTypeId(typeId);
    }

//    @Override
//    public List<Blog> queryTypeByTypeId(Long typeId) {
//        return typeMapper.queryTypeByTypeId(typeId);
//    }

    @Override
    public List<Type> queryAllTypes() {
        List<Type> typeList =  typeMapper.queryAllTypes();
        return typeList;
    }

    @Override
    public Type addType(Type type) {
        Type typeToDao = new Type();
        BeanUtils.copyProperties(type,typeToDao);
        Integer effectedRows = typeMapper.addType(typeToDao);
        if (effectedRows.equals(1)) {
            return typeToDao;
        }
        return type;
    }

    @Override
    public Integer deleteTypeByTypeId(Long typeId) {
        return typeMapper.deleteTypeByTypeId(typeId);
    }

}
