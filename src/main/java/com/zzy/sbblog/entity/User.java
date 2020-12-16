package com.zzy.sbblog.entity;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户角色
 * @author zzy
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private Long id;
    private String nickname;
    private String username;
    private String password;
    private String email;
    private String avatar;
    /**
     * TODO 将type更改为role
     */
    private Integer type;
    private Date createTime;
    private Date updateTime;

    private List<Blog> blogs = new ArrayList<>();

}
