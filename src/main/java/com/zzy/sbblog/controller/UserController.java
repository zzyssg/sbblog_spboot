package com.zzy.sbblog.controller;

import com.zzy.sbblog.entity.ResponseBean;
import com.zzy.sbblog.entity.User;
import com.zzy.sbblog.service.UserService;
import com.zzy.sbblog.vo.LoginVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zzy
 * @Date 2020/12/15 14:51
 */
@RestController
@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;

    @PostMapping("/frontLogin")
    public ResponseBean frontLogin(@RequestBody User user) {
        //仅验证登录
        //根据用户名查询密码，若密码正确，则验证通过
        String username = user.getUsername();
        User userQuery = userService.queryUserByName(username);

        if (userQuery == null || !userQuery.getPassword().equals(user.getPassword())) {
            LoginVO loginVO = LoginVO.builder().nickname(username).username(username).build();
            return new ResponseBean("000", "登录失败！", loginVO);
        }
        LoginVO loginVO = LoginVO.builder()
                .nickname(username)
                .username(username)
                .avatar(userQuery.getAvatar())
                .token("123456")
                .userId(userQuery.getId())
                .build();
        return new ResponseBean("001", "success", loginVO);

    }

    @PostMapping("/frontRegister")
    public LoginVO frontRegister(@RequestBody User user) {
        String nickname = user.getNickname();
        //查询是否已经有同名用户，有的话 返回异常信息重新注册
        //如果未查到同名用户

        String password = user.getPassword();
        log.info("用户名:{}", nickname);
        log.info("用户密码:{}", password);
        return LoginVO.builder()
                .nickname(nickname)
                .password(password)
                .build();

    }
}
