package com.example.demo.Education;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class EducationService {
   private final EducationRepository educationRepository ; 
   private static final Logger logger = LoggerFactory.getLogger(EducationService.class);
   @Autowired
   public EducationService(EducationRepository educationRepository){
    this.educationRepository = educationRepository;
   }

   @Transactional
   public EducationDTO createEducationService (EducationDTO educ){
    try {
        Education educationInserted = this.educationRepository.save(Education.fromDTO(educ));
        return EducationDTO.fromEntity(educationInserted);
    } catch (Exception e) {
        logger.error(e.getMessage(), e);
        throw new RuntimeException(e.getMessage());
    }
   }

   @Transactional
   public List<EducationDTO> findAllEducationsService (){
    try {
        List<Education> educations = this.educationRepository.findAll();
        return educations.stream()
                         .map(EducationDTO::fromEntity)
                         .collect(Collectors.toList());
    } catch (Exception e) {
        logger.error(e.getMessage(), e);
        throw new RuntimeException(e.getMessage());
    }
   }

   @Transactional
   public Optional<EducationDTO> findOneEducationService (long id){
    try {
        Optional<Education> educationIfFound = this.educationRepository.findById(id);
        return educationIfFound.map(EducationDTO::fromEntity);
    } catch (Exception e) {
        logger.error(e.getMessage(), e);
        throw new RuntimeException(e.getMessage());
    }
   }

   @Transactional
   public String destroyOneEducationService(long id){
    try {
         this.educationRepository.deleteById(id);
        return "Education has been deleted";
    } catch (Exception e) {
        logger.error(e.getMessage(), e);
        throw new RuntimeException(e.getMessage());
    }
   }
    
}