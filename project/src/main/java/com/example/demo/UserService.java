package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepositoty;
    public void  registerUser(UserRegistrationReq req){
        userRepositoty.save(req.toUserEntity());
    }
}
