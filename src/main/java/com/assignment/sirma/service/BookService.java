package com.assignment.sirma.service;

import com.assignment.sirma.model.Project;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BookService {
    Project addProject(Project project);
    List<Project> getAllProjects();
    Project updateProject(Project project);
    String deleteProject(Long projectId);
}
