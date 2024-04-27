package com.assignment.sirma.service;

import com.assignment.sirma.model.Project;

import java.util.List;

public interface ProjectService {
    Project addProject(Project project); // service layer method to add new project
    List<Project> getAllProjects(); // service layer method to get the list of all projects form the database.
    Project updateProject(Project project); // service layer method to update an existing project.
    String deleteProject(Long projectId); // service layer method to delete an existing project.
}
