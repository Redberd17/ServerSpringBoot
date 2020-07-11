package com.chugunova.myproject.service;

import com.chugunova.myproject.model.User;
import com.chugunova.myproject.model.UserSecurity;

public interface UserService {
    User getUser(String username);

    UserSecurity findByUsername(String username);
}
