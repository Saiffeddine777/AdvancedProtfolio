package  com.example.demo.Projects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "projects")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Project {
    @Id
    @Column(name = "id" , nullable = false ,unique = true )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    @Column(name = "name" , nullable = false )
    private String name;
    @Column(name = "description" , nullable = false )
    private String description;
    @Column(name = "repoUrl" , nullable = false )
    private String repoUrl;
    @Column(name = "techStack" , nullable = false )
    private String techStack; 
    @Column(name = "liveUrl" , nullable = false )
    private String liveUrl;

    public static Project fromDTO(ProjectDTO projectDTO){
        return new Project(
            projectDTO.getId(),
            projectDTO.getName(),
            projectDTO.getDescription(),
            projectDTO.getRepoUrl(),
            projectDTO.getTechStack(),
            projectDTO.getLiveUrl()
        );
    }

}