package com.example.demo.Skills;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
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
    
}