package com.example.SimpleRegistrationForm.Dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDto {

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 25, message = "First name should be between 2 and 25 characters")
    private String firstName;

    @NotBlank(message = "Second name is required")
    @Size(min = 2, max = 25, message = "First name should be between 2 and 25 characters")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email")
    private String email;

    @NotBlank(message = "State selection is required")
    private String state;

    @NotNull(message = "Please specify if you have an ID")
    private boolean id;

    private MultipartFile idFile;

}
