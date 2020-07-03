package com.chugunova.myproject.service;

import com.chugunova.myproject.DAO.UsersDAO;
import com.chugunova.myproject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UsersDAO usersDAO;

    @Autowired
    public UserServiceImpl(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    @Override
    public User getUser(String username) {
        return this.usersDAO.getUser(username);
    }
}
