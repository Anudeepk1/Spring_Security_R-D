package com.learning.SpringSecurity.service;

import com.learning.SpringSecurity.CustomUserDetails;
import com.learning.SpringSecurity.entity.User;
import com.learning.SpringSecurity.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepo){
        this.userRepository = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> currUser = userRepository.findByUserName(username);
        if(Objects.isNull(currUser)){
            throw new UsernameNotFoundException("User is not registered in the database");
        } else {
            return new CustomUserDetails(currUser.get());
        }
    }
}
