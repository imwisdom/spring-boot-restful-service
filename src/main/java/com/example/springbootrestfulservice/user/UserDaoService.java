package com.example.springbootrestfulservice.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service    //@Component도 되는데 어떠한 용도로 사용될 것인지 정확히 Service로 명시
public class UserDaoService {
    private static List<User> users = new ArrayList();
    private static int usersCount = 3;
    static {
        users.add(new User(1, "wisdom", new Date(), "pass1", "701010-1111111"));
        users.add(new User(2, "jihye", new Date(), "pass2", "801010-1111111"));
        users.add(new User(3, "wisdomjihye", new Date(), "pass2", "901010-1111111"));
    }
    public List<User> findAll(){
        return users;
    }
    public User save(User user){
        if(user.getId() == null){
            user.setId(++usersCount);
        }
        users.add(user);
        return user;
    }
    public User findOne(int id){
        for(User user : users){
            if(user.getId() == id) return user;
        }
        return null;
    }
    public User deleteById(int id){
        Iterator<User> iterator = users.iterator();
        while(iterator.hasNext()){
            User user = iterator.next();
            if(user.getId() == id){
                iterator.remove();
                return user;
            }
        }
        return null;
    }
}
