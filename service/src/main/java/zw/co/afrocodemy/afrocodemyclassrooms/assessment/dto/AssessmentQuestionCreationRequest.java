package zw.co.afrocodemy.afrocodemyclassrooms.assessment.dto;

import lombok.Builder;
import lombok.Data;
import zw.co.afrocodemy.afrocodemyclassrooms.assessment.AssessmentType;

import java.util.Set;

@Data
@Builder
public class AssessmentQuestionCreationRequest {
    private String creatorUsername;
    private String question;
    private Long assessmentId;
    private AssessmentType assessmentType;
    private Set<PossibleAnswerRequest> possibleAnswers;
}
