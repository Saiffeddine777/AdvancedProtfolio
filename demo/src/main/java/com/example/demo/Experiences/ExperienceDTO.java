package com.example.demo.Experiences;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExperienceDTO {

    private long id;
    private String position;
    private String company;
    private String startDate;
    private String endDate;
    private String location;
    private String description;

    public static ExperienceDTO fromEntity(Experience experience) {
        return new ExperienceDTO(
            experience.getId(),
            experience.getPosition(),
            experience.getCompany(),
            experience.getStartDate(),
            experience.getEndDate(),
            experience.getLocation(),
            experience.getDescription()
        );
    }
}
