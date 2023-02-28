package com.example.workflowmanagementbackend.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLogin {
    @NotBlank(message = "Username is empty")
    private String username;
    @NotBlank(message = "Password is empty")
    private String password;
}
