package com.raullorca.FCProject.controller;

import com.raullorca.FCProject.entity.AppUser;
import com.raullorca.FCProject.repository.UserRepository;
import com.raullorca.FCProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping("/users")
    public ResponseEntity<List<AppUser>>getUsers(){
        return ResponseEntity.ok().body(userService.findAll());
    }

    @PostMapping("/users/myuser")
    public ResponseEntity<AppUser>getMyUser(String email){
        if(userService.findByEmail(email)!= null){
            return ResponseEntity.ok().body(userService.findByEmail(email));
        }else{
            return ResponseEntity.badRequest().build();
        }
    }
}
