package com.chugunova.myproject.rest;

import com.chugunova.myproject.dto.AuthenticationRequestDto;
import com.chugunova.myproject.model.UserSecurity;
import com.chugunova.myproject.security.jwt.JwtTokenProvider;
import com.chugunova.myproject.service.UserService;
import com.chugunova.myproject.utils.EncryptedPasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/auth/")
public class AuthenticationRestController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    @Autowired
    public AuthenticationRestController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String username = requestDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            UserSecurity user = userService.findByUsername(username);

            if (user == null) {
                System.out.println("User with username: " + username + " not found");
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping("registration")
    public ResponseEntity registration(@RequestBody AuthenticationRequestDto requestDto) {
        String username = requestDto.getUsername();

        if (username.isEmpty()) {
            System.out.println("Username is empty");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username is empty");
        }

        String password = EncryptedPasswordUtils.encryptPassword(requestDto.getPassword());

        UserSecurity user = userService.findByUsername(username);

        if (user != null) {
            System.out.println("User with username: " + username + " already exists");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with username: " + username + " already exists");
        }

        userService.addNewUser(username, password);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
