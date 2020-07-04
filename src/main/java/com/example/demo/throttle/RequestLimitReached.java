package com.example.demo.throttle;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.TOO_MANY_REQUESTS,reason = "Too many requests")
public class RequestLimitReached extends RuntimeException {

    public RequestLimitReached(String userId, EndpointMethod endpointMethod) {
        super(String.format("User: %s, reached calls limit for method: %s", userId, endpointMethod.toString()));
    }

}
