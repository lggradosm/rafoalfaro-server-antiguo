package com.rafoalfaro.server.service;

import com.rafoalfaro.server.domain.User;
import com.rafoalfaro.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User create(User user){
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
       return userRepository.save(user);
    }

    public User findUserByUsername(String username){
        User user = userRepository.findUserByUsername(username);
        user.setPassword("");
        return user;
    }
}
