package com.chugunova.myproject.mapper;

public class MessageMapper {
    public static String GET_ALL_MESSAGE = "select mes_from as mesFrom, mes_text as mesText, date_trunc('second', cast(mes_date_time as timestamp)) as mesDateTime from messages order by mes_date_time";
    public static String SEND_MESSAGE = "insert into messages (mes_from, mes_date_time, mes_text) values (?, localtimestamp, ?)";
}
