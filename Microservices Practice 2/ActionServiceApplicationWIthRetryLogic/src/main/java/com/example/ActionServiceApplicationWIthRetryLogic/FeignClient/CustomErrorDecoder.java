package com.example.ActionServiceApplicationWIthRetryLogic.FeignClient;

import com.example.ActionServiceApplicationWIthRetryLogic.Exception.ResourceNotFoundException;
import com.example.ActionServiceApplicationWIthRetryLogic.Response.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;

public class CustomErrorDecoder implements ErrorDecoder {

    private final ObjectMapper mapper;
    private final ErrorDecoder errorDecoder = new Default();

    public CustomErrorDecoder(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Exception decode(String s, Response response) {
        return switch(response.status()){
            case 404 -> handle404(response);
            default -> errorDecoder.decode(s, response);
        };
    }

    private Exception handle404(Response response) {
        System.err.println("ACTION-SERVICE: Calling QUERY-SERVICE has returned a status code of 404");
        throw new ResourceNotFoundException(extractMessage(response));
    }

    private String extractMessage(Response response) {
        try(InputStream body = response.body().asInputStream()) {
            if(body != null) {
                return mapper.readValue(body, ErrorResponse.class).getMessage();
            }
            return "Response has no body!";
        } catch (IOException e) {
            return "Unable to extract message";
        }
    }
}
