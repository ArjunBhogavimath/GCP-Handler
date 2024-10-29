package com.project_test.testing_spring_project.bashcommandrunner.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.stereotype.Service;

@Service
public class BashCommandService {

    // Execute the bash command to get the project number from the project ID
    public String runBashCommand(String projectId) throws Exception {
        // Construct the gcloud command to get the project number
        String command = "gcloud projects describe " + projectId + " --format='value(projectNumber)'";

        // Use ProcessBuilder to execute the command
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash", "-c", command);

        // Start the process
        Process process = processBuilder.start();

        // Capture the output of the command
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder output = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }

        // Wait for the command to complete
        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("Command failed with exit code: " + exitCode);
        }

        // Return the command output (trim to remove any trailing new lines)
        return output.toString().trim();
    }

    // Dynamically form the service agent email based on the project number
    public String getServiceAgentEmail(String projectNumber) {
        return "service-" + projectNumber + "@gs-project-accounts.iam.gserviceaccount.com";
    }
}
