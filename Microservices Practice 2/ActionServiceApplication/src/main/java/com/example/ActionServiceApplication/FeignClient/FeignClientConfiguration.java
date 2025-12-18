package com.example.ActionServiceApplication.FeignClient;

import com.example.ActionServiceApplication.Exception.CustomErrorDecoder;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

public class FeignClientConfiguration {

    @Bean
    public ErrorDecoder errorDecoder(){
        return new CustomErrorDecoder(new ObjectMapper());
    }
}
