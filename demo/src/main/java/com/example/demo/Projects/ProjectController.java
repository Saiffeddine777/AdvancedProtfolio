package com.example.demo.Projects;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectService projectService;
    private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);
    @Autowired     
    public ProjectController(ProjectService projectService){
      this.projectService = projectService;
    }

    @PostMapping("/create")
    public ResponseEntity <?> postOneProject (@RequestBody ProjectDTO projectDTO){
        try {
            ProjectDTO project = this.projectService.createProjectService(projectDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(project);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed to save the project :" + e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity <?> findAllProjects (){
        try {
            List<ProjectDTO> projects = this.projectService.findAllProjectsService();
            return ResponseEntity.status(HttpStatus.OK).body(projects);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed to fetch the projects :" + e.getMessage());
        }
    }

    @GetMapping("/one/{id}")
    public ResponseEntity <?> findOneProject (@PathVariable("id") long id){
        try {
           Optional <ProjectDTO> projectIfFound = this.projectService.findOneProjectService(id);
           if (projectIfFound.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(projectIfFound.get());
           }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project is not found");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed to fetch the Project :" + e.getMessage());
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity <?> deleteOneProject (@PathVariable("id") long id){
        try {
            String result = this.projectService.destroyOneProjectService(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed to delete the project :" + e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity <?> updateOneProject (@PathVariable("id") long id , @RequestBody ProjectDTO dto){
        try {
            String result = this.projectService.modifyOneProjectService(id, dto);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed to delete the project :" + e.getMessage());
        }
    }
    
}