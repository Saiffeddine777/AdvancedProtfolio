package com.example.demo.Skills;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SkillService {
    private final SkillRepository skillRepository;
    private static final Logger logger = LoggerFactory.getLogger(SkillService.class);

    @Autowired
    public SkillService (SkillRepository skillRepository){
      this.skillRepository = skillRepository;
    }

    @Transactional
    public SkillDTO createSkillService (SkillDTO skillDTO){
        try {
            Skill skillInserted = this.skillRepository.save(Skill.fromDTO(skillDTO));
            return SkillDTO.fromEntity(skillInserted);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }
    }
    
    
}