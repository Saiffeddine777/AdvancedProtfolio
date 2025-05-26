package com.example.demo.Skills;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.example.demo.Skills.Skill.NatureEnum;
import com.example.demo.Skills.Skill.LevelEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SkillDTO {


    private long id;
    private String name ;
    private NatureEnum nature;
    private int grade;
    private LevelEnum level; 
    private String description;
    public static SkillDTO fromEntity (Skill skill){
        return new SkillDTO(
            skill.getId(),
            skill.getName(),
            skill.getNature(),
            skill.getGrade(),
            skill.getLevel(),
            skill.getDescription()
            
        );
    }
    
}