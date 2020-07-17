package com.chugunova.myproject.service;

import com.chugunova.myproject.DAO.MessageDAO;
import com.chugunova.myproject.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageDAO messageDAO;

    @Autowired
    public MessageServiceImpl(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    @Override
    public List<Message> getAllMessage() {
        return messageDAO.getAllMessage();
    }

    @Override
    public void sendMessage(String mesFrom, String mesText) {
        messageDAO.sendMessage(mesFrom, mesText);
    }
}
