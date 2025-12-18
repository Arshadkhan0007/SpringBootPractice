package com.example.ActuatorsPractice.CustomEndpoints;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Endpoint(id = "featureflags")
public class FeatureFlagEndpoint {

    // Use a ConcurrentHashMap for thread safety.
    private final Map<String, Boolean> featureFlags = new ConcurrentHashMap<>();

    public FeatureFlagEndpoint() {
        featureFlags.put("GPS", false);
        featureFlags.put("Dark-UI-Mode", true);
    }

    @ReadOperation
    public Map<String, Boolean> getAllFeatures(){
        return this.featureFlags;
    }

    @ReadOperation
    public Map<String, Object> getFeature(@Selector String featureName){
        boolean isEnabled = this.featureFlags.getOrDefault(featureName, false);
        return Map.of("feature", featureName, "isEnabled", isEnabled);
    }

    @WriteOperation
    public Map<String, Boolean> configureFeature(@Selector String featureName, Boolean isEnabled){
        this.featureFlags.put(featureName, isEnabled);
        return this.featureFlags;
    }
}
