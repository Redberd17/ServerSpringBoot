package com.chugunova.myproject.mapper;

public class UserMapper {
    public static String GET_USER_BY_USERNAME = "select user_name as userName\n" +
            "from users\n" +
            "where user_name = ?";

    public static String USER_FOR_SPRING_SECURITY = "select user_name as login, user_password as password, user_role as role\n" +
            "from users\n" +
            "where user_name = ?";
}
