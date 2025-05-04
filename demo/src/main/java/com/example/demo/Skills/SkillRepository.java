package com.example.demo.Skills;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository  extends JpaRepository<Skill , Long> {
    
}