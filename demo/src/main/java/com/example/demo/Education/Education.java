package com.example.demo.Education;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "education")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , unique = true , nullable = false) 
    private long id;

    @Column(name = "institution", nullable = false)
    private String institution;

    @Column(name = "degree", nullable = false)
    private String degree;

    @Column(name = "field_of_study", nullable = false)
    private String fieldOfStudy;

    @Column(name = "start_year", nullable = false)
    private int startYear;

    @Column(name = "end_year")
    private int endYear;

    public static Education fromDTO(EducationDTO dto) {
        return new Education(
            dto.getId(),
            dto.getInstitution(),
            dto.getDegree(),
            dto.getFieldOfStudy(),
            dto.getStartYear(),
            dto.getEndYear()
        );
    }
}
