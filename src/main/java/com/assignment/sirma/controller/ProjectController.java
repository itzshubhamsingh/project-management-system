package com.assignment.sirma.controller;

import com.assignment.sirma.model.Project;
import com.assignment.sirma.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    private ProjectService service;

    // Constructor Injection
    @Autowired
    public ProjectController(ProjectService service){
        this.service=service;
    }

    // Expose "/projects" to add a new Project
    @PostMapping
    public ResponseEntity<?> addProject(@RequestBody Project project){
        try{
            Project projectObj = service.addProject(project);
            return new ResponseEntity<>(projectObj, HttpStatus.CREATED);
        }
        catch (RuntimeException e) {
            // Use the exception message from ControllerExceptionHandler
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal Server Error: " + e.getMessage());
        }
    }

    // Expose "/projects" to get the list of all existing projects
    @GetMapping
    public ResponseEntity<?> getAllProjects() {
        try {
            List<Project> projects = service.getAllProjects();
            return ResponseEntity.ok(projects);
        } catch (RuntimeException e) {
            // Use the exception message from ControllerExceptionHandler
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal Server Error: " + e.getMessage());
        }
    }

    // Expose "/projects/update" to update an existing project
    @PutMapping("/update")
    public ResponseEntity<?> updateProject(@RequestBody Project project) {
        try {
            Project updatedProject = service.updateProject(project);
            return ResponseEntity.ok(updatedProject);
        } catch (RuntimeException e) {
            // Use the exception message from ControllerExceptionHandler
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal Server Error: " + e.getMessage());
        }
    }

    // Expose "/projects/delete" to delete an existing project.
    @DeleteMapping("/delete/{projectId}")
    public ResponseEntity<String> deleteProject(@PathVariable Long projectId) {
        try {
            service.deleteProject(projectId);
            return ResponseEntity.ok("Project successfully deleted");
        } catch (RuntimeException e) {
            // Use the exception message from ControllerExceptionHandler
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal Server Error: " + e.getMessage());
        }
    }

}
