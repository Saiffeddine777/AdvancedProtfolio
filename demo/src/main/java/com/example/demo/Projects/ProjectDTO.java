package  com.example.demo.Projects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProjectDTO {

    private long id ;
    private String name;
    private String description;
    private String repoUrl;
    private String techStack; 
    private String liveUrl;

    public static ProjectDTO fromEntity(Project project){
        return new ProjectDTO(
            project.getId(),
            project.getName(),
            project.getDescription(),
            project.getRepoUrl(),
            project.getTechStack(),
            project.getLiveUrl()
        );
    }
}