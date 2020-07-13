package com.example.demo;


import com.example.demo.filters.JWTRequestFilter;
import com.example.demo.models.AuthenticationRequest;
import com.example.demo.models.AuthenticationResponse;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.throttle.Throttle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;


@RestController

public class HelloController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailService userDetailService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/hello")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String hello() {
        return "Hello " + JWTRequestFilter.UserClaim+ ". You have Admin role";
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @Throttle(timeFrameInSeconds = 60, calls = 2)
    public String user(@RequestHeader HttpHeaders headers) {
        return "Hello " + JWTRequestFilter.UserClaim;
    }

    @PostMapping("/token")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect Username or Password", e);
        }

        final UserDetails userDetails = userDetailService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PostMapping("/sign-up")
    @CrossOrigin(origins = "http://localhost:3000")
    public String signUp(@RequestBody User user) {

        if(userRepository.findByUsername(user.getUsername())==null) {
            Set<Role> roles= new HashSet<>();
            Role role= roleRepository.findByName("USER");
            roles.add(role);
            user.setRoles(roles);
            user.setPassword(bcryptEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return "Success";
        }
        else{
            return "Username Already Exsist";
        }
    }

}
