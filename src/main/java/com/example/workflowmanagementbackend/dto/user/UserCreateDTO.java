package com.example.workflowmanagementbackend.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDTO {

    @NotBlank(message = "Username is mandatory")
    @Size(min = 4, max = 255, message = "Minimum username length: 4 characters")
    private String username;
    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Minimum password length: 8 characters")
    private String password;
    @NotBlank(message = "Email is mandatory")
    @NotNull(message = "Email is mandatory")
    @Email(message = "Invalid email format")
    private String email;

}