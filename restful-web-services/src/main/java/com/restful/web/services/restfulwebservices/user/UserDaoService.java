package com.restful.web.services.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

    private static int userCount = 3;
    
    private static List<Users> users = new ArrayList<>();

    static {
        users.add(new Users(1, "Owolabi", new Date()));
        users.add(new Users(2, "Fatai", new Date()));
        users.add(new Users(3, "Gedion", new Date()));
    }

    public List<Users> findAll() {
        return users;
    }
    
    public Users save(Users user) {
        if (user.getId() == null) {
            user.setId(++userCount);
        }

        users.add(user);
        return user;
    }
    
    public Users findOne(int id) {
        for (Users user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public Users deleteById(int id) {
        Iterator<Users> iterator = users.iterator();
        while (iterator.hasNext()) {
            Users user = iterator.next();
            if (user.getId() == id) {
                iterator.remove();
                return user;
            }
        }
        return null;
    }
}

