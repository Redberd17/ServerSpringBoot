package com.chugunova.myproject.rest;

import com.chugunova.myproject.model.Dream;
import com.chugunova.myproject.model.User;
import com.chugunova.myproject.service.DreamService;
import com.chugunova.myproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping({"/users/"})
public class RestController {
    private final DreamService dreamService;
    private final UserService userService;

    @Autowired
    public RestController(DreamService dreamService, UserService userService) {
        this.dreamService = dreamService;
        this.userService = userService;
    }

    @GetMapping({"dreams/{username}"})
    public ResponseEntity<List<Dream>> getUserDreams(@PathVariable(name = "username") String username) {
        List<Dream> dream = this.dreamService.getUserDreams(username);
        if (dream == null) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(dream, HttpStatus.OK);
        }
    }

    @GetMapping({"{username}"})
    public ResponseEntity<User> getUser(@PathVariable(name = "username") String username) {
        User user = this.userService.getUser(username);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }
}
