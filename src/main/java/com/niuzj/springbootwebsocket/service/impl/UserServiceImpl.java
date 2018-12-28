package com.niuzj.springbootwebsocket.service.impl;

import com.niuzj.springbootwebsocket.dao.UserDao;
import com.niuzj.springbootwebsocket.model.User;
import com.niuzj.springbootwebsocket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public User login(User user) {
        try {
            List<User> list = userDao.select(user);
            if (list == null || list.size() == 0 || list.size() > 1){
                return null;
            }
            return list.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
