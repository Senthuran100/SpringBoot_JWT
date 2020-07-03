package com.example.demo.throttle;

import java.util.Optional;

public class UserIdProvider {

    public static Optional<String> getCurrentuserId(){
        return Optional.of("test@domain.com");
    }
}
