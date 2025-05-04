package com.example.demo.Education;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/educations")
public class EducationController {
    private final EducationService educationService;
    private static final Logger logger = LoggerFactory.getLogger(EducationService.class);

    @Autowired
    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> postEducationController(@RequestBody EducationDTO educat) {
        try {
            EducationDTO educationInserted = this.educationService.createEducationService(educat);
            return ResponseEntity.status(HttpStatus.CREATED).body(educationInserted);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create the education : " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAllEducationsController() {
        try {
            List<EducationDTO> educations = this.educationService.findAllEducationsService();
            return ResponseEntity.status(HttpStatus.FOUND).body(educations);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to find the educations : " + e.getMessage());
        }
    }

    @GetMapping("/one/{id}")
    public ResponseEntity<?> findOneEducationController(@PathVariable("id") long id) {
        try {
            Optional<EducationDTO> education = this.educationService.findOneEducationService(id);
            if (education.isPresent()) {
                return ResponseEntity.status(HttpStatus.FOUND).body(education.get());
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("the eduction does not exist");

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to find the education: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOneEducationController(@PathVariable("id") long id) {
        try {
            String result = this.educationService.destroyOneEducationService(id);
            return ResponseEntity.status(HttpStatus.OK).body(result);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete Education: " + e.getMessage());
        }
    }

}