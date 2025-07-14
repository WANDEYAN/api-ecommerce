package br.com.ecommerce.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecommerce.api.dto.UserRegisterDTO;
import br.com.ecommerce.api.model.User;
import br.com.ecommerce.api.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserRegisterDTO userRegisterDTO){
        User newUser = userService.registerUser(userRegisterDTO);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
}
