package com.example.demo.Skills;

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
@RequestMapping("/api/skills")
public class SkillController {
    private final SkillService  skillService;
    private static final Logger logger = LoggerFactory.getLogger(SkillController.class);

    @Autowired
    public SkillController(SkillService skillService){
      this.skillService = skillService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> postSkillController(@RequestBody SkillDTO skillDTO){
        try {
           SkillDTO skill = this.skillService.createSkillService(skillDTO);
           return ResponseEntity.status(HttpStatus.CREATED).body(skill);
        } catch (Exception e) {
           logger.error(e.getMessage(), e);
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed to save the Skill :" + e.getMessage());
        }
    } 

    @GetMapping("/all")
    public ResponseEntity<?> getAllSkillsController(){
      try {
         List<SkillDTO> skills = this.skillService.findAllTheSkillsService();
         return ResponseEntity.status(HttpStatus.OK).body(skills);
      } catch (Exception e) {
         logger.error(e.getMessage(), e);
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed to fetch the Skills :" + e.getMessage());
      }
    } 
        @GetMapping("/allnodescription")
    public ResponseEntity<?> getAllSkillsWithoutDescriptionController(){
      try {
         List<SkillSummaryProjection> skills = this.skillService.findAllTheSkillsWithoutDescriptionService();
         return ResponseEntity.status(HttpStatus.OK).body(skills);
      } catch (Exception e) {
         logger.error(e.getMessage(), e);
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed to fetch the Skills :" + e.getMessage());
      }
    } 

    @GetMapping("/one/{id}")
    public ResponseEntity<?> getOneSkillController(@PathVariable("id") Long id){
      try {
         Optional<SkillDTO> skillOptional = this.skillService.findOneSkillService(id);
         if (skillOptional.isPresent()){
           return ResponseEntity.status(HttpStatus.OK).body(skillOptional.get());
         }
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The Skill requested does not exist");
      } catch (Exception e) {
         logger.error(e.getMessage(), e);
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed to save the Skill :" + e.getMessage());
      }
    } 
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateOneSkillController( @PathVariable("id") Long id , @RequestBody SkillDTO data){
      try {
         String result = this.skillService.modifyOneSkillService(id , data);
         return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
      } catch (Exception e) {
         logger.error(e.getMessage(), e);
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed to save the Skill :" + e.getMessage());
      }
    } 
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOneSkillController(@PathVariable("id") Long id ){
      try {
         String  result = this.skillService.removeOneSkillService(id);
         return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
      } catch (Exception e) {
         logger.error(e.getMessage(), e);
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed to save the Skill :" + e.getMessage());
      }
  } 
    
}