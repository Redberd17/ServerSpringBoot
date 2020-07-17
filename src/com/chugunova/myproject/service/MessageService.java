package com.chugunova.myproject.service;

import com.chugunova.myproject.model.Message;

import java.util.List;

public interface MessageService {
    List<Message> getAllMessage();

    void sendMessage(String mesFrom, String mesText);

}
