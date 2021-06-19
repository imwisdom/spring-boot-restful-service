package com.example.springbootrestfulservice.user;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/admin")   //사용자가 호출하는 api 주소에 무조건 '/admin'을 적어야 함
public class AdminUserController {
    private UserDaoService service;

    @Autowired
    private MessageSource messageSource;    //같은 type에 있는 bean을 자동 주입

    public AdminUserController(UserDaoService service){
        this.service = service;
    }

//    @GetMapping("/users")
//    public List<User> retrieveAllUsers(){
//        return service.findAll();
//    }
    @GetMapping("/users")
    public MappingJacksonValue retrieveAllUsers(){
        List<User> users = service.findAll();

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name", "joinDate", "password");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(users);
        mapping.setFilters(filters);
        return mapping;
    }

//    // ex) GET /users/1
//    //path variable의 type을 지정한 것에 따라 id가 자동적으로 type에 맞게 매핑
//    @GetMapping("/users/{id}")
//    public MappingJacksonValue retrieveUser(@PathVariable int id){
//        User user = service.findOne(id);
//
//        if(user == null){
//            throw new UserNotFoundException(String.format("ID[%s] not found", id));
//        }
//        //500 internal server error
//
//        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
//                .filterOutAllExcept("id", "name", "password", "ssn");
//        //User 클래스에서 @JsonFilter("UserInfo")의 "UserInfo"가 filter의 id가 됨
//        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);
//        MappingJacksonValue mapping = new MappingJacksonValue(user);
//        mapping.setFilters(filters);
//        return mapping;
//    }
    // ex) GET /admin/users/1 -> /admin/v1/users/1
    //path variable의 type을 지정한 것에 따라 id가 자동적으로 type에 맞게 매핑
    @GetMapping("/v1/users/{id}")   //version 1.0
    public MappingJacksonValue retrieveUserV1(@PathVariable int id){
        User user = service.findOne(id);

        if(user == null){
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }
        //500 internal server error

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name", "password", "ssn");
        //User 클래스에서 @JsonFilter("UserInfo")의 "UserInfo"가 filter의 id가 됨
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(user);
        mapping.setFilters(filters);
        return mapping;
    }
    @GetMapping("/v2/users/{id}")   //version 2.0
    public MappingJacksonValue retrieveUserV2(@PathVariable int id){
        User user = service.findOne(id);

        if(user == null){
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }
        //500 internal server error

        //User -> User2
        UserV2 userV2 = new UserV2();
        BeanUtils.copyProperties(user, userV2); //두 인스턴스 간의 공통적인 필드가 있으면 그 값을 copy
        userV2.setGrade("VIP");

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name", "joinDate", "grade");
        //User 클래스에서 @JsonFilter("UserInfo")의 "UserInfo"가 filter의 id가 됨
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoV2", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(userV2);
        mapping.setFilters(filters);
        return mapping;
    }
    @GetMapping(path = "/hello-world-internationalized")
    public String helloWorldInternationalized(@RequestHeader(name="Accept-Language", required = false) Locale locale){
        return messageSource.getMessage("greeting.message", null, locale);
    }

}
