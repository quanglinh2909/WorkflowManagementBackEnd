package com.example.workflowmanagementbackend.controller;

import com.example.workflowmanagementbackend.dto.user.UserCreateDTO;
import com.example.workflowmanagementbackend.dto.user.UserUpdate;
import com.example.workflowmanagementbackend.exception.ExitsException;
import com.example.workflowmanagementbackend.model.Response;
import com.example.workflowmanagementbackend.model.User;
import com.example.workflowmanagementbackend.service.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(tags = "users")
@RequiredArgsConstructor
@RequestMapping("/api/")
public class UserController {
    private final UserService userService;
    @PutMapping("user")
    public ResponseEntity<Response> update(@RequestBody @Valid UserUpdate user) throws ExitsException {
        return ResponseEntity.ok(userService.update(user));
    }

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAll() throws ExitsException {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("user/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") Long id) throws ExitsException {
        return ResponseEntity.ok(userService.getById(id));
    }
    @GetMapping("/profile")
    public ResponseEntity<User> getProfile(@RequestAttribute("user") String user) throws ExitsException {
        return ResponseEntity.ok(userService.getByUsername(user));
    }
    //Attribute user to request


}
