package com.chugunova.myproject.service;

import com.chugunova.myproject.model.UserSecurity;

public interface UserService {

    UserSecurity findByUsername(String username);

    void addNewUser(String username, String password);

}
