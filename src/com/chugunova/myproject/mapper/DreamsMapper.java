package com.chugunova.myproject.mapper;

public class DreamsMapper {
    public static String GET_ALL_USER_DREAMS = "select dream_name as dreamName, dream_date as dreamDate, dream_text as dreamText, dream_duration as dreamDuration\nfrom dreams\nwhere id_user in (select id_user from users where user_name = ?)";
}
