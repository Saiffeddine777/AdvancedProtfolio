package com.example.demo.Skills;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Table(name ="skills")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Skill {

    public enum NatureEnum {
        FrontEnd, BackEnd , Infrastructure, Database 
    }
    public enum LevelEnum {
        Beginner , Intermediate , Advanced
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , unique = true , nullable = false)
    private long id;

    @Column(name = "name" , nullable = false)
    private String name ;

    @Column(name = "nature" , nullable = false)
    @Enumerated(EnumType.STRING)
    private NatureEnum nature;
    
    @Column(name = "grade" , nullable = false)
    private int grade;

    @Enumerated(EnumType.STRING)
    @Column(name = "level" , nullable = false)
    private LevelEnum level; 
    
    @Lob
    @Column ( name ="description" , columnDefinition = "TEXT" , nullable  = false)
    private String description;

    public static Skill fromDTO (SkillDTO skillDTO){
        return new Skill(
            skillDTO.getId(),
            skillDTO.getName(),
            skillDTO.getNature(),
            skillDTO.getGrade(),
            skillDTO.getLevel(),
            skillDTO.getDescription()
        );
    }
    
}   