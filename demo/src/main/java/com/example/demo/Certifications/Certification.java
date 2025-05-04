package com.example.demo.Certifications;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "certifications")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Certification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",unique = true, nullable = false)
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "issuer", nullable = false)
    private String issuer;

    @Column(name = "issue_date", nullable = false)
    private String issueDate; 

    @Column(name = "expiration_date")
    private String expirationDate;

    @Column(name = "credential_url")
    private String credentialUrl;

    public static Certification fromDTO(CertificationDTO dto) {
        return new Certification(
            dto.getId(),
            dto.getTitle(),
            dto.getIssuer(),
            dto.getIssueDate(),
            dto.getExpirationDate(),
            dto.getCredentialUrl()
        );
    }
}
