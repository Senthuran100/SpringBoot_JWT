package com.example.demo.throttle;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Service
public class ThrottlingManager {

    private final Map<EndpointMethod, Map<String,Cache<Long,Long>>> ENDPOINT_THROTTLE_MAPPING = new ConcurrentHashMap<>();

    void throttleRequest(EndpointMethod endpointMethod, String userid, ThrottlingConfig throttlingConfig){
        Map<String,Cache<Long,Long>> endpointThrottle = ENDPOINT_THROTTLE_MAPPING.computeIfAbsent(endpointMethod,k -> new HashMap<>());
        Cache<Long,Long> autoExpiringUserCallCounter = endpointThrottle.computeIfAbsent(userid, k -> buildCacheWhichRemovesEntriesAfterTimeFrame(throttlingConfig));
        Long callsCount = autoExpiringUserCallCounter.size();
        if(requestLimitReached(throttlingConfig,callsCount)){
            autoExpiringUserCallCounter.cleanUp();
            if (requestLimitReached(throttlingConfig, autoExpiringUserCallCounter.size())) {
                throw new RequestLimitReached(userid, endpointMethod);
            }
        } else {
            long randomKeysToIncreaseCounter = new SecureRandom().nextLong();
            autoExpiringUserCallCounter.put(randomKeysToIncreaseCounter,randomKeysToIncreaseCounter);
        }
    }

    private boolean requestLimitReached(ThrottlingConfig throttlingConfig, Long callCount){
        return callCount != null && callCount + 1 > throttlingConfig.getCallsCount();
    }

    private Cache<Long,Long> buildCacheWhichRemovesEntriesAfterTimeFrame(ThrottlingConfig throttlingConfig){
        return CacheBuilder.newBuilder()
                .expireAfterWrite(throttlingConfig.getTimeFrameInSeconds(), TimeUnit.SECONDS)
                .build();
    }

}
