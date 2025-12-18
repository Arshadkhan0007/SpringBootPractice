package com.example.ActionServiceApplicationWithInterceptors.Context;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserContext {

    private String correlationId;
    private String authToken;
    private int userId;

}
