package zw.co.afrocodemy.afrocodemyclassrooms.assessment.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PossibleAnswerRequest {
    private Long id;
    private String associatedUsername;
    private String answerText;
    private Boolean isCorrect;
}
