package com.chugunova.myproject.service;

import com.chugunova.myproject.DAO.DreamsDAO;
import com.chugunova.myproject.model.Dream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DreamServiceImpl implements DreamService {
    private final DreamsDAO dreamsDAO;

    @Autowired
    public DreamServiceImpl(DreamsDAO dreamsDAO) {
        this.dreamsDAO = dreamsDAO;
    }

    public List<Dream> getUserDreams(String username) {
        return this.dreamsDAO.getUserDreams(username);
    }
}
