package com.example.demo;

import com.example.demo.models.Usermodel;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Usermodel user = userRepository.findByUsername(userName);
        if (user == null){
            throw new UsernameNotFoundException(userName);
        }
        return new User(user.getUsername(),user.getPassword(), new ArrayList<>());
    }
}
