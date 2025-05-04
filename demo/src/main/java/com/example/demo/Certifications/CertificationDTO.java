package com.example.demo.Certifications;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CertificationDTO {

    private long id;
    private String title;
    private String issuer;
    private String issueDate;
    private String expirationDate;
    private String credentialUrl;

    public static CertificationDTO fromEntity(Certification certification) {
        return new CertificationDTO(
            certification.getId(),
            certification.getTitle(),
            certification.getIssuer(),
            certification.getIssueDate(),
            certification.getExpirationDate(),
            certification.getCredentialUrl()
        );
    }
}
