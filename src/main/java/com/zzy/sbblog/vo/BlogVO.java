package com.zzy.sbblog.vo;

import com.zzy.sbblog.entity.Type;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlogVO {
    private Long id;
    private String title;
    private String content;
    private String firstPicture;
    private String description;
    private boolean sharement;
    private boolean recommend;
    private Date updateTime;
    private Long typeId;
    private Type type;

}
