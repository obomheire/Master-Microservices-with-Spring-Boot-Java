package com.jpa.basic.jpain10steps;

import java.util.List;
import java.util.Optional;

import com.jpa.basic.jpain10steps.entity.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import com.jpa.basic.jpain10steps.service.UserRepository;

import org.springframework.stereotype.Component;

@Component
public class UserRepositoryCommandLineRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(UserRepositoryCommandLineRunner.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... arg0) throws Exception {
        Users user = new Users("Obomheire Morobo", "User");
        userRepository.save(user);
        log.info("New user is created with" + user);

        Optional<Users> userWithIdOne = userRepository.findById(1l);
        log.info("New user is created with" + userWithIdOne);

        List<Users> users = userRepository.findAll();
        log.info("All users" + users);
    }
}
