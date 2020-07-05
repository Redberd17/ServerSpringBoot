package com.chugunova.myproject.service;

import com.chugunova.myproject.DAO.AdviceDurDAO;
import com.chugunova.myproject.model.AdviceDuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdviceDurServiceImpl implements AdviceDurService {
    private final AdviceDurDAO adviceDurDAO;

    @Autowired
    public AdviceDurServiceImpl(AdviceDurDAO adviceDurDAO) {
        this.adviceDurDAO = adviceDurDAO;
    }

    @Override
    public List<AdviceDuration> getAdviceDuration() {
        return this.adviceDurDAO.getAdviceDur();
    }
}
