package com.zzy.sbblog.vo;

import com.zzy.sbblog.entity.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zzy
 * @Date 2020/10/27 17:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TypesAndTagsVo {
    private List<Type> types;
//    private List<Tags> tags;

}
