package com.example.demo.Education;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EducationDTO {

    private long id;
    private String institution;
    private String degree;
    private String fieldOfStudy;
    private int startYear;
    private int endYear;

    public static EducationDTO fromEntity(Education education) {
        return new EducationDTO(
            education.getId(),
            education.getInstitution(),
            education.getDegree(),
            education.getFieldOfStudy(),
            education.getStartYear(),
            education.getEndYear()
        );
    }
}
