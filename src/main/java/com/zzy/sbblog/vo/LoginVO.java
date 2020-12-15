package com.zzy.sbblog.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zzy
 * @Date 2020/12/15 14:56
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class LoginVO {
    private Long userId;
    private String nickname;
    private String username;
    private String password;
    private String token;


}
