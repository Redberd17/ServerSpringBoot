package com.chugunova.myproject.service;

import com.chugunova.myproject.model.Dream;

import java.util.List;

public interface DreamService {
    List<Dream> getUserDreams(String username);

    void addUserDreams(String dreamName, String dreamText, String username, Double dreamDuration);
}
