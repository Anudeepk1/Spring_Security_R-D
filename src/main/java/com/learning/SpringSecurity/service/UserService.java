package com.learning.SpringSecurity.service;

import com.learning.SpringSecurity.entity.User;
import com.learning.SpringSecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTService jwtService;

    public User register(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public String verifyUser(User user) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUserName(), user.getPassword()
                ));
        if(authenticate.isAuthenticated()){
            return jwtService.generateToken(user);
        } else {
            return "Failed";
        }
        /*Optional<User> currUser = userRepository.findByUserName(user.getUserName());
        if(!Objects.isNull(currUser)){
            return "Success";
        } else {
            return "Falied";
        }*/
    }
}
