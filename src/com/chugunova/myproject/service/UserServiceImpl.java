package com.chugunova.myproject.service;

import com.chugunova.myproject.DAO.UsersDAO;
import com.chugunova.myproject.model.User;
import com.chugunova.myproject.model.UserSecurity;
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
        return usersDAO.getUser(username);
    }

    @Override
    public UserSecurity findByUsername(String username) {
        return usersDAO.userForSecurity(username);
    }
}
