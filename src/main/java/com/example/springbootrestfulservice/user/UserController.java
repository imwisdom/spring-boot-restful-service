package com.example.springbootrestfulservice.user;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private UserDaoService service;

    public UserController(UserDaoService service){
        this.service = service;
    }
    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }
    // ex) GET /users/1
    //path variable의 type을 지정한 것에 따라 id가 자동적으로 type에 맞게 매핑
    @GetMapping("/user/{id}")
    public User retrieveUser(@PathVariable int id){
        return service.findOne(id);
    }
    @PostMapping("/users")
    public void createUser(@RequestBody User user){
        User savedUser = service.save(user);
    }

}
