package com.example.demo.Projects;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProjectService {
   private final ProjectRepository projectRepository;
   private static final Logger logger = LoggerFactory.getLogger(ProjectService.class);
   @Autowired
   public ProjectService(ProjectRepository projectRepository){
     this.projectRepository = projectRepository; 
   }

   @Transactional
   public ProjectDTO createProjectService (ProjectDTO projectDto){
    try {
        Project projectInserted = this.projectRepository.save(Project.fromDTO(projectDto));
        return ProjectDTO.fromEntity(projectInserted);
    } catch (Exception e) {
        logger.error(e.getMessage(), e);
        throw new RuntimeException(e.getMessage());
    }
   }
   @Transactional
   public List<ProjectDTO> findAllProjectsService (){
    try {
        List<Project> projects = this.projectRepository.findAll();
        return projects.stream()
                       .map(ProjectDTO::fromEntity)
                       .collect(Collectors.toList());
    } catch (Exception e) {
        logger.error(e.getMessage(), e);
        throw new RuntimeException(e.getMessage());
    }
   }
   @Transactional
   public Optional<ProjectDTO> findOneProjectService (long id){
    try {
        Optional<Project> project = this.projectRepository.findById(id);
        return project.map(ProjectDTO::fromEntity);
    } catch (Exception e) {
        logger.error(e.getMessage(), e);
        throw new RuntimeException(e.getMessage());
    }
   }
   @Transactional
   public String destroyOneProjectService(long id){
    try {
        this.projectRepository.deleteById(id);
        return "The Project has been deleted";
    } catch (Exception e) {
        logger.error(e.getMessage(), e);
        throw new RuntimeException(e.getMessage());
    }
   }


   @Transactional
   public String modifyOneProjectService(long id , ProjectDTO data){
    try {
        if (data !=null){
            Project projectToModify = this.projectRepository.findById(id).orElseThrow(()-> new RuntimeException("Project does not exist"));
            ProjectDTO dtoToModify = ProjectDTO.fromEntity(projectToModify);
            if (data.getName()!= null && !data.getName().equals(dtoToModify.getName())){
                dtoToModify.setName(data.getName());
            }
            if (data.getLiveUrl()!= null && !data.getLiveUrl().equals(dtoToModify.getLiveUrl())){
                dtoToModify.setLiveUrl(data.getLiveUrl());
            }
            if (data.getRepoUrl()!= null && !data.getRepoUrl().equals(dtoToModify.getRepoUrl())){
                dtoToModify.setRepoUrl(data.getRepoUrl());
            }
            if (data.getDescription()!= null && !data.getDescription().equals(dtoToModify.getDescription())){
                dtoToModify.setDescription(data.getDescription());
            }
            if (data.getTechStack()!= null && !data.getTechStack().equals(dtoToModify.getTechStack())){
                dtoToModify.setTechStack(data.getTechStack());
            }
            this.projectRepository.save(Project.fromDTO(dtoToModify));
            return "the Project has been Modified";
        }
        
        return "the object that you have insertedin null";
    } catch (Exception e) {
        logger.error(e.getMessage(), e);
        throw new RuntimeException(e.getMessage());
    }
   }
   

}

