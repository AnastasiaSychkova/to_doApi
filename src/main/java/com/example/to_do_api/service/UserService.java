package com.example.to_do_api.service;

import com.example.to_do_api.entity.User;
import com.example.to_do_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User addUser(User user){
        return userRepository.save(user);
    }

    public com.example.to_do_api.entity.User getUserByMail(String mail){
        return userRepository.findUserByMail(mail);
    }
}
