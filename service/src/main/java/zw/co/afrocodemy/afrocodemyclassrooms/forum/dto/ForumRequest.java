package zw.co.afrocodemy.afrocodemyclassrooms.forum.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ForumRequest {
    private Long id;
    private String associatedUsername;
    private String name;
    private Long courseId;
}
