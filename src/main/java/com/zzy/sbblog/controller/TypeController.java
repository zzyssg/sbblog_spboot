package com.zzy.sbblog.controller;

import com.zzy.sbblog.entity.ResponseBean;
import com.zzy.sbblog.entity.Type;
import com.zzy.sbblog.exception.CommonException;
import com.zzy.sbblog.service.TypeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zzy
 * @Date 2020/10/27 17:33
 */
@RestController
@RequestMapping("/type")
public class TypeController {

    @Resource
    private TypeService typeService;

    @GetMapping("/queryTypeByTypeId")
    public ResponseBean queryTypeByTypeId(@RequestParam("typeId") Long typeId){
        ResponseBean responseBean;
        Type type = typeService.queryTypeByTypeId(typeId);
        responseBean = new ResponseBean("001","success",type);
        return responseBean;
    }

    @GetMapping("/queryAllTypes")
    public ResponseBean queryAllTypes(){
        ResponseBean responseBean;
        List<Type> typeList = typeService.queryAllTypes();
        responseBean = new ResponseBean("001", "success", typeList);
        return responseBean;
    }

    @PostMapping("/addType")
    public ResponseBean addType(@RequestBody Type type){
        ResponseBean responseBean;
        /*验证typeName是否已经存在，若存在，返回异常；否则继续添加*/
        Type typeFromSql = typeService.queryTypeByName(type.getName());
        if(typeFromSql != null){
            throw new CommonException("000","type已经存在，请更换！");
        }
        Type typeRtn = typeService.addType(type);
        if (typeRtn.getId() != null) {
            responseBean = new ResponseBean("001", "success", typeRtn);
        } else {
            responseBean = new ResponseBean("000", "failed", typeRtn);
        }
        return responseBean;
    }

    @GetMapping("/deleteTypeByTypeId")
    public ResponseBean deleteTypeByTypeId(@RequestParam("typeId") Long typeId){
        ResponseBean responseBean;
        Type type = typeService.queryTypeByTypeId(typeId);
        Integer effectedRow = typeService.deleteTypeByTypeId(typeId);
        if(effectedRow == 1){
            responseBean = new ResponseBean("001","success",type);
        }else {
            responseBean = new ResponseBean("000", "failed", type);
        }
        return responseBean;
    }

}
