package com.example.demo.Skills;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Transactional
    public List<SkillDTO> findAllTheSkillsService (){
        try {
            List<Skill> allSkills = this.skillRepository.findAll();
            return allSkills.stream()
            .map(SkillDTO::fromEntity)
            .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public Optional<SkillDTO> findOneSkillService (Long id){
        try {
            Optional<Skill> skill = this.skillRepository.findById(id);
            return skill.map(SkillDTO::fromEntity);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public String  removeOneSkillService (Long id){
        try {
            this.skillRepository.deleteById(id);
            return "Skill has been deleted";
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }
    }


    @Transactional
    public String  modifyOneSkillService (Long id , SkillDTO data){
        try {
            if (data != null){
                Skill skillToUpdate = this.skillRepository.findById(id).orElseThrow(()-> new RuntimeException("The Skill has not been found !"));
                SkillDTO skillToUpdateDTO = SkillDTO.fromEntity(skillToUpdate);

                if (data.getGrade()!=0 && (data.getGrade() != skillToUpdateDTO.getGrade()) ){
                    skillToUpdateDTO.setGrade(data.getGrade());
                }
                if (data.getLevel() != null && (!data.getLevel().equals(skillToUpdateDTO.getLevel()))){
                    skillToUpdateDTO.setLevel(data.getLevel());
                }
                if (data.getName() != null && (!data.getName().equals(skillToUpdateDTO.getName()))){
                    skillToUpdateDTO.setName(data.getName());
                }
                if (data.getNature() != null && (!data.getNature().equals(skillToUpdateDTO.getNature()))){
                    skillToUpdateDTO.setNature(data.getNature());
                }  
                this.skillRepository.save(Skill.fromDTO(skillToUpdateDTO));
                return "The skill has been updated";
            }

            return "There is nothing to modify";
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }
    }


    
    
    
}