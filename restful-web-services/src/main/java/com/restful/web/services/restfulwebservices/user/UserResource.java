package com.restful.web.services.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService userDaoService;

    @GetMapping("/users")
    public List<Users> retrieveAllUsers() {
        return userDaoService.findAll();
    }

    // @GetMapping("/users/{id}")
    // public Users retrieveUser(@PathVariable int id) {
    // Users user = userDaoService.findOne(id);
    // if (user == null) {
    // throw new UserNotFoundException(String.format("User with id: %s not found",
    // id));
    // }

    // return user;
    // }

    @GetMapping("/users/{id}")
    public EntityModel<Users> retrieveUser(@PathVariable int id) {
        Users user = userDaoService.findOne(id);
        if (user == null) {
            throw new UserNotFoundException(String.format("User with id: %s not found", id));
        }
        EntityModel<Users> model = EntityModel.of(user);
        WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).retrieveAllUsers());

        model.add(linkToUsers.withRel("all-users"));
        return model;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        Users user = userDaoService.deleteById(id);
        if (user == null) {
            throw new UserNotFoundException(String.format("User with id: %s not found", id));
        }

    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody Users user) {
        Users savedUser = userDaoService.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
}
