package com.example.springbootrestfulservice.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id){
        return service.findOne(id);
    }
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User savedUser = service.save(user);
        //사용자에게 요청값을 변환해주기 위한 코드
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
        //201 Created
    }

}
