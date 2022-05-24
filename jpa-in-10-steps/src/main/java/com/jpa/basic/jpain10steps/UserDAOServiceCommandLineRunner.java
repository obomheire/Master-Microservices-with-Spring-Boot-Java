package com.jpa.basic.jpain10steps;

import com.jpa.basic.jpain10steps.entity.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import com.jpa.basic.jpain10steps.service.UserDAOService;
import org.springframework.stereotype.Component;

@Component
public class UserDAOServiceCommandLineRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(UserDAOServiceCommandLineRunner.class);

    @Autowired
    private UserDAOService userDaoService;

    @Override
    public void run(String... arg0) throws Exception {
        Users user = new Users("Zack Bello", "Admin");
        userDaoService.insert(user);
        log.info("New user is created with" + user);
    }
}
