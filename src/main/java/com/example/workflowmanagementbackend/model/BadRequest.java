package com.example.workflowmanagementbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BadRequest {
    private int code;
    private Map<String, String> errors;
}
