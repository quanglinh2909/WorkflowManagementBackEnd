package com.example.workflowmanagementbackend.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdate {

    @NotNull(message = "Id null")
    private Long id;
    @Size(min = 4, max = 255, message = "Minimum username length: 4 characters")
    private String username;
    @Size(min = 8, message = "Minimum password length: 8 characters")
    private String password;
    @Email(message = "Invalid email format")
    private String email;

}
