package zw.co.afrocodemy.afrocodemyclassrooms.assessment.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class MultipleChoiceQuestionDto {
    private Long id;
    private String question;
    private Set<PossibleAnswerResponse> answers;
}
