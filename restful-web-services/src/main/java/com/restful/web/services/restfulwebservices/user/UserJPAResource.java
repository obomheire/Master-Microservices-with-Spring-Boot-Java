package com.restful.web.services.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
public class UserJPAResource {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping("jpa/users")
    public List<Users> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("jpa/users/{id}")
    public EntityModel<Users> retrieveUser(@PathVariable int id) {
        Optional<Users> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException(String.format("User with id: %s not found", id));
        }
        EntityModel<Users> model = EntityModel.of(user.get());
        WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).retrieveAllUsers());

        model.add(linkToUsers.withRel("all-users"));
        return model;
    }

    @DeleteMapping("jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    @PostMapping("jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody Users user) {
        Users savedUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("jpa/users/{id}/posts")
    public List<Post> retrieveUserPost(@PathVariable int id) {
        Optional<Users> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException(String.format("User with id: %s not found", id));
        }
        return user.get().getPosts();

    }

    @PostMapping("jpa/users/{id}/posts")
    public ResponseEntity<Object> createPostByUser(@PathVariable int id, @RequestBody Post post) {
        Optional<Users> findUser = userRepository.findById(id);
        if (!findUser.isPresent()) {
            throw new UserNotFoundException(String.format("User with id: %s not found", id));
        }
        Users user = findUser.get();

        post.setUsers(user);

        postRepository.save(post);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

}
