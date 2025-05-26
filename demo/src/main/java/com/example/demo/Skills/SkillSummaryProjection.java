package com.example.demo.Skills;

import com.example.demo.Skills.Skill.LevelEnum;
import com.example.demo.Skills.Skill.NatureEnum;

public interface SkillSummaryProjection {

    long getId();
    String getName();
    NatureEnum getNature();
    LevelEnum getLevel();
    int getGrade();
}
