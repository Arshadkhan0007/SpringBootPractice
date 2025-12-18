package com.example.ActionServiceApplicationWIthRetryLogic.FeignClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

public class FeignClientConfig {

    @Bean
    public ErrorDecoder errorResponse() {
        return new CustomErrorDecoder(new ObjectMapper());
    }

}
