package com.example.demo.Skills;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository  extends JpaRepository<Skill , Long> {
 @Query("SELECT s FROM Skill s")
 List<SkillSummaryProjection> findAllProjectedBy();   
}