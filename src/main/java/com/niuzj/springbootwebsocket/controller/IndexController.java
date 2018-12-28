package com.niuzj.springbootwebsocket.controller;

import com.niuzj.springbootwebsocket.model.Result;
import com.niuzj.springbootwebsocket.model.User;
import com.niuzj.springbootwebsocket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class IndexController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login.json")
    public Result login(User user, HttpSession session){
        Object obj = session.getAttribute("user");
        if (obj != null){
            return Result.newSuccess(obj);
        }
        if (user == null || user.getUsername() == null || user.getPassword() == null){
            return Result.newFailed("参数错误");
        }
        User user1 = userService.login(user);
        if (user1 == null){
            return Result.newFailed("用户名或密码错误");
        }
        user1.setPassword(null);
        session.setAttribute("user", user1);
        return Result.newSuccess(user1);
    }


}
