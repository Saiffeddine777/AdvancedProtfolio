package com.example.demo.Experiences;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "experiences")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id" , unique = true , nullable = false)
    private long id;

    @Column(name = "position", nullable = false)
    private String position;

    @Column(name = "company", nullable = false)
    private String company;

    @Column(name = "start_date", nullable = false)
    private String startDate; // You can switch to LocalDate if needed

    @Column(name = "end_date")
    private String endDate; // Can be null for current positions

    @Column(name = "location")
    private String location;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    public static Experience fromDTO(ExperienceDTO dto) {
        return new Experience(
            dto.getId(),
            dto.getPosition(),
            dto.getCompany(),
            dto.getStartDate(),
            dto.getEndDate(),
            dto.getLocation(),
            dto.getDescription()
        );
    }
}
