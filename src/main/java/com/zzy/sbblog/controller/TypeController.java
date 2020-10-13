package com.zzy.sbblog.controller;

import com.zzy.sbblog.entity.ResponseBean;
import com.zzy.sbblog.entity.Type;
import com.zzy.sbblog.service.TypeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
