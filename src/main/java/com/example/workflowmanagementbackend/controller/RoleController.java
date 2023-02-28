package com.example.workflowmanagementbackend.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "users")
@RequiredArgsConstructor
@RequestMapping("/api/")
public class RoleController {
    @GetMapping("/demo")
    public String demo(@RequestAttribute("user") String user ) {
        return "login dfgdfg";
    }

}
