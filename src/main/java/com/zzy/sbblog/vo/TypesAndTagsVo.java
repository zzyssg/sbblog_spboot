package com.zzy.sbblog.vo;

import com.zzy.sbblog.entity.Type;

import java.util.List;

public class TypesAndTagsVo {
    private List<Type> types;
//    private List<Tags> tags;


    public TypesAndTagsVo() {
    }

    public TypesAndTagsVo(List<Type> types) {
        this.types = types;
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

    @Override
    public String toString() {
        return "TypesAndTagsVo{" +
                "types=" + types +
                '}';
    }
}
