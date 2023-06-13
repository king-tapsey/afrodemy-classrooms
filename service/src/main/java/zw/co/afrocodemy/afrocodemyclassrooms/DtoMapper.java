package zw.co.afrocodemy.afrocodemyclassrooms;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zw.co.afrocodemy.afrocodemyclassrooms.assessment.*;
import zw.co.afrocodemy.afrocodemyclassrooms.assessment.dto.*;
import zw.co.afrocodemy.afrocodemyclassrooms.exceptions.InvalidRequestException;

import java.time.ZonedDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DtoMapper {
    private final AssessmentRepository assessmentRepository;
    private final MultipleChoiceQuestionRepository multipleChoiceRepository;
    private final PossibleAnswerRepository possibleAnswerRepository;

    public MultipleChoiceAssessmentQuestion createRequestToMultipleChoiceQuestion(final AssessmentQuestionCreationRequest request){
        MultipleChoiceAssessmentQuestion question = new MultipleChoiceAssessmentQuestion();
        question.setCreatorUsername(request.getCreatorUsername());
        question.setCreatedDate(ZonedDateTime.now());
        question.setQuestion(request.getQuestion());
        question.setAssessment(assessmentRepository.findById(request.getAssessmentId()).orElse(null));
        question.setAssessmentType(request.getAssessmentType());
        question.setPossibleAnswers(request.getPossibleAnswers().stream()
                .map(this::dtoToPossibleAnswerCreation).collect(Collectors.toSet()));

        PossibleAnswerRequest correctAnswerDto = request.getPossibleAnswers().stream()
                .filter(answer -> answer.getIsCorrect().equals(true)).findFirst().get();
        PossibleAnswer correctAnswer = question.getPossibleAnswers().stream()
                .filter(answer -> answer.getAnswerText().equals(correctAnswerDto.getAnswerText()))
                .findFirst().get();

        return question;
    }
    public MultipleChoiceAssessmentQuestion updateRequestToMultipleChoiceQuestion(final AssessmentQuestionUpdateRequest request){
        MultipleChoiceAssessmentQuestion question = multipleChoiceRepository.findById(request.getId()).get();
        PossibleAnswer answerFromRequest = possibleAnswerRepository.findById(request.getId()).get();

        if(question.getQuestion().equals(request.getQuestion()) && question.getAnswer().equals(answerFromRequest)){
            return question;
        }

        question.setModifiedDate(ZonedDateTime.now());
        question.setModifierUsername(request.getModifierUsername());
        question.setQuestion(request.getQuestion());
        question.setAnswer(answerFromRequest);

        return question;
    }
    public PossibleAnswer dtoToPossibleAnswerCreation(PossibleAnswerRequest request){
        return PossibleAnswer.builder()
                .creatorUsername(request.getAssociatedUsername())
                .createdDate(ZonedDateTime.now())
                .answerText(request.getAnswerText())
                .build();
    }
    public MultipleChoiceQuestionDto multipleChoiceQuestionToDto(final MultipleChoiceAssessmentQuestion question){
        Set<PossibleAnswerResponse> possibleAnswerResponseSet = question.getPossibleAnswers().stream()
                .map(this::possibleAnswerToResponseDto).collect(Collectors.toSet());
        return MultipleChoiceQuestionDto.builder()
                .id(question.getId())
                .question(question.getQuestion())
                .answers(possibleAnswerResponseSet)
                .build();
    }
    public PossibleAnswerResponse possibleAnswerToResponseDto(final PossibleAnswer possibleAnswer){
        return PossibleAnswerResponse.builder()
                .id(possibleAnswer.getId())
                .answerText(possibleAnswer.getAnswerText())
                .build();
    }
    public PossibleAnswer requestToPossibleAnswerUpdate(final PossibleAnswerRequest request){
        PossibleAnswer answer = possibleAnswerRepository.findById(request.getId()).orElse(null);
        if(answer == null){
            throw new InvalidRequestException("Possible answer with Id: " + request.getId() + " not found");
        }
        answer.setModifiedDate(ZonedDateTime.now());
        answer.setModifierUsername(request.getAssociatedUsername());
        answer.setAnswerText(request.getAnswerText());

        return answer;
    }
}
