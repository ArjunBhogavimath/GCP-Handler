package com.project_test.testing_spring_project.bashcommandrunner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CloudProjectService {

    @Autowired
    private BashCommandService bashCommandService;

    public String getServiceAgent(String projectId) {
        try {
            // Get the project number dynamically using the bash command
            String projectNumber = bashCommandService.runBashCommand(projectId);

            // Form the service agent email based on the project number
            String serviceAgentEmail = bashCommandService.getServiceAgentEmail(projectNumber);

            return serviceAgentEmail;

        } catch (Exception e) {
            throw new RuntimeException("Failed to get the service agent: " + e.getMessage(), e);
        }
    }
}
