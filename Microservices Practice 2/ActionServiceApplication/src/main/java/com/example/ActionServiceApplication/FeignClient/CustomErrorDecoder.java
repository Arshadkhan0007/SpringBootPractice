package com.example.ActionServiceApplication.Exception;

import com.example.ActionServiceApplication.Response.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import org.apache.hc.core5.http.HttpStatus;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Component
public class CustomErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper;
    private final ErrorDecoder errorDecoder = new Default();

    public CustomErrorDecoder(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Exception decode(String methodKey, Response response) {

        return switch (response.status()) {
            case 404 -> handle404(response);
            default -> new RuntimeException("Unexpected error has occurred from " + methodKey + ": " + response);
        };

    }


    private Exception handle404(Response response) {
        System.err.println("ACTION-SERVICE: Calling QUERY-SERVICE has returned status code of 404");
        return new ResourceNotFoundException(extractBody(response));
    }

    private String extractBody(Response response) {
        try(InputStream body = response.body().asInputStream()) {
            if(body != null) {
                return objectMapper.readValue(body, ErrorResponse.class).getMessage();
            }
            return "Response body is null";
        } catch (IOException e) {
            return "Unable to extract response body";
        }
    }
}
