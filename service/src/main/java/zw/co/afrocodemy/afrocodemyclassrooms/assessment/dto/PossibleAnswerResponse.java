package zw.co.afrocodemy.afrocodemyclassrooms.assessment.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PossibleAnswerResponse {
    private Long id;
    private String answerText;
}
