package zw.co.afrocodemy.afrocodemyclassrooms.assessment.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class AssessmentQuestionUpdateRequest {
    private Long id;
    private String modifierUsername;
    private String question;
    private Long answerId;
}
