package com.assignment.sirma.service;

import com.assignment.sirma.model.Project;
import com.assignment.sirma.repository.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class BookServiceImpl implements BookService{

    private ProjectRepository repository;
    @Autowired
    public BookServiceImpl(ProjectRepository repository){
        this.repository = repository;
    }

    @Override
    public Project addProject(Project project) {
        try {
            return repository.save(project);
        } catch (DataAccessException e) {
            // Log the exception and throw a specific exception or handle it as needed
            throw new RuntimeException("Error adding project", e);
        }
    }

    @Override
    public List<Project> getAllProjects() {
        try {
            List<Project> projectList = new ArrayList<>();
            repository.findAll().forEach(projectList::add);

            if (projectList.isEmpty()) {
                throw new EntityNotFoundException("No projects found");
            }
            return projectList;
        } catch (DataAccessException e) {
            throw new RuntimeException("Error fetching projects", e);
        }
    }

    @Override
    public Project updateProject(Project project) {
        Long projectId = project.getProjectId();
        try {
            if (!repository.existsById(projectId)) {
                throw new EntityNotFoundException("Project not found with ID: " + projectId);
            }
            return repository.save(project);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error updating project", e);
        }
    }

    @Override
    public String deleteProject(Long projectId) {
        try {
            if (!repository.existsById(projectId)) {
                throw new EntityNotFoundException("Project not found with ID: " + projectId);
            }
            repository.deleteById(projectId);
            return "Deleted";
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Error deleting project: " + e.getMessage());
        } catch (DataAccessException e) {
            throw new RuntimeException("Error deleting project", e);
        }
    }
}
