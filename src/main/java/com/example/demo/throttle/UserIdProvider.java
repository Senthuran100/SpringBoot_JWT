package com.example.demo.throttle;

import java.util.Optional;
import com.example.demo.filters.JWTRequestFilter;

public class UserIdProvider {

    public static Optional<String> getCurrentuserId(){
        return Optional.of(JWTRequestFilter.UserClaim);
    }
}
