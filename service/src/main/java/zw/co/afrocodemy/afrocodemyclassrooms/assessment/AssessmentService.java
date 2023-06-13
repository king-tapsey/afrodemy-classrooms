package zw.co.afrocodemy.afrocodemyclassrooms.assessment;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import zw.co.afrocodemy.afrocodemyclassrooms.assessment.dto.AssessmentQuestionCreationRequest;
import zw.co.afrocodemy.afrocodemyclassrooms.assessment.dto.AssessmentQuestionUpdateRequest;
import zw.co.afrocodemy.afrocodemyclassrooms.assessment.dto.PossibleAnswerRequest;

public interface AssessmentService {
    ResponseEntity<?> getQuestions();
    ResponseEntity<?> getQuestionsByAssessment(Long assessmentId, Pageable pageable);
    ResponseEntity<?> getQuestionsByCreatorUsername(String username, Pageable pageable);
    ResponseEntity<?> getQuestionsByModifierUsername(String username, Pageable pageable);
    ResponseEntity<?> createQuestion(AssessmentQuestionCreationRequest request);
    ResponseEntity<?> updateQuestion(AssessmentQuestionUpdateRequest request);
    ResponseEntity<?> updatePossibleAnswer(PossibleAnswerRequest possibleAnswerRequest);
    ResponseEntity<?> deleteQuestion(Long id);
    ResponseEntity<?> attemptAssessment(String username, Long assessmentId);
    ResponseEntity<?> attemptMultipleChoiceQuestion(Long questionId, Long assessmentAttemptId, Long chosenAnswerId, String username);
    ResponseEntity<?> submitAssessment(String username, Long assessmentId);
}
