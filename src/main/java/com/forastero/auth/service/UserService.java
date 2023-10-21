package com.forastero.auth.service;

import com.forastero.auth.model.User;
import com.forastero.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    public User updateUser(@PathVariable Long id,
                           @RequestBody User user) {
        if (userRepository.existsById(id)) {
            user.setId(id);
            return userRepository.save(user);
        } else {
            return null;
        }
    }

    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

    @PostConstruct
    void seedUser(){
        createUser(
                User.builder()
                        .username("ciro")
                        .email("ciroandrey@outlook.com")
                        .build()
        );
    }
}
