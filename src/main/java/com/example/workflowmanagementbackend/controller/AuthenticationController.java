package com.example.workflowmanagementbackend.controller;

import com.example.workflowmanagementbackend.dto.user.UserCreateDTO;
import com.example.workflowmanagementbackend.dto.user.UserLogin;
import com.example.workflowmanagementbackend.exception.ExitsException;
import com.example.workflowmanagementbackend.model.AuthenticationResponse;
import com.example.workflowmanagementbackend.model.User;
import com.example.workflowmanagementbackend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@Api(tags = "auth")
@RequiredArgsConstructor
@RequestMapping("/api/")
public class AuthenticationController {
    private final UserService userService;

    @PostMapping("login")
    @ApiOperation(value = "login api")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid UserLogin user) throws ExitsException {
        return  ResponseEntity.ok(userService.login(user));
    }

    @PostMapping("signup")
    public ResponseEntity<AuthenticationResponse> signup(@RequestBody @Valid UserCreateDTO user) throws ExitsException {
        return ResponseEntity.ok(userService.singUp(user));
    }


}
