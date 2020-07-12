package com.chugunova.myproject.service;

import com.chugunova.myproject.DAO.UsersDAO;
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
    public UserSecurity findByUsername(String username) {
        return usersDAO.userForSecurity(username);
    }

    @Override
    public void addNewUser(String username, String password) {
        usersDAO.addNewUser(username, password);
    }


}
