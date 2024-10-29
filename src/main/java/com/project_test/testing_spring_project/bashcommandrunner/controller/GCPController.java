package com.project_test.testing_spring_project.bashcommandrunner.controller;

import com.project_test.testing_spring_project.bashcommandrunner.service.CloudProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GCPController {

    @Autowired
    private CloudProjectService cloudProjectService;

    @GetMapping("/get-service-agent")
    public ResponseEntity<String> getServiceAgent(@RequestParam String projectId) {
        try {
            String serviceAgent = cloudProjectService.getServiceAgent(projectId);
            return ResponseEntity.ok(serviceAgent);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}
