package com.zzy.sbblog.service.impl;

import com.zzy.sbblog.dao.TypeMapper;
import com.zzy.sbblog.entity.Type;
import com.zzy.sbblog.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zzy
 * @Date 2020/10/27 17:33
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TypeServiceImpl implements TypeService {

    @Resource
    private TypeMapper typeMapper;

    @Override
    public Type queryTypeByTypeId(Long typeId) {
        return typeMapper.queryTypeByTypeId(typeId);
    }

    @Override
    public Type queryTypeByName(String typeName) {
        return typeMapper.queryTypeByName(typeName);
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
