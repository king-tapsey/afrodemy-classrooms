package zw.co.afrocodemy.afrocodemyclassrooms.forum.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ForumCommentRequest {
    private Long id;
    private String associatedUsername;
    private String text;
    private Long questionId;
    private Long answerId;
}
