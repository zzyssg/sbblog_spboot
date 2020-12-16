package com.zzy.sbblog.controller;

import com.zzy.sbblog.entity.ResponseBean;
import com.zzy.sbblog.entity.User;
import com.zzy.sbblog.service.UserService;
import com.zzy.sbblog.vo.LoginVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zzy
 * @Date 2020/10/27 17:33
 */
@RestController
@Slf4j
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseBean login(@RequestBody User user){

        /*验证用户名和密码，
        * 用户不存在：显示用户不存在
        * 用户存在但密码不正确：显示密码错误
        * 均正确：返回true，
        * */
        ResponseBean res;
        /*用户名是否为空*/
        if (user.getUsername() == null
                || "".equals(user.getUsername())) {
            res = new ResponseBean("000", "用户名不能为空", user);
            return res;
        }
        /*查询用户存在与否*/
        User userFind = userService.queryUserByName(user.getUsername());
        if(userFind == null){
            res = new ResponseBean("000","用户不存在",userFind);
            return res;
        }
        if(!checkPasswd(userFind.getPassword(),user.getPassword())){
            /*验证密码是否正确*/
            res = new ResponseBean("000", "密码不正确", user);
            return res;
        }
        /*用户名存在并且密码正确，返回用户的除了密码的一切信息*/
        /*数据库里根据username查询所有的信息返回*/
        res = new ResponseBean("001", "success", userFind);
        return res;
    }

    private boolean checkPasswd(String passwordFind,String password) {
        return passwordFind.equals(password);
    }

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
