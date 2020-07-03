package com.chugunova.myproject.mapper;

public class UserMapper {
    public static String GET_USER_BY_USERNAME = "select user_name as userName\n" +
            "from users\n" +
            "where user_name = ?";
}
