package zw.co.afrocodemy.afrocodemyclassrooms.assessment.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import zw.co.afrocodemy.afrocodemyclassrooms.DtoMapper;
import zw.co.afrocodemy.afrocodemyclassrooms.assessment.*;
import zw.co.afrocodemy.afrocodemyclassrooms.assessment.dto.*;
import zw.co.afrocodemy.afrocodemyclassrooms.exceptions.InvalidRequestException;
import zw.co.afrocodemy.afrocodemyclassrooms.exceptions.UnknownQuestionTypeException;

import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class AssessmentServiceImpl implements AssessmentService {
    private final DtoMapper mapper;
    private final AssessmentQuestionRepository assessmentQuestionRepository;
    private final AssessmentRepository assessmentRepository;
    private final AssessmentAttemptRepository assesssmentAttemptRepository;
    private final AttemptedQuestionRepository attemptedQuestionRepository;
    private final MultipleChoiceQuestionRepository multipleChoiceQuestionRepository;
    private final PossibleAnswerRepository possibleAnswerRepository;

    @Override
    public ResponseEntity<?> getQuestions() {
        List<AssessmentQuestion> questions = assessmentQuestionRepository.findAll();
        List<MultipleChoiceQuestionDto> questionsResponse = new ArrayList<>();
        //TODO: initialize lists for other question types here
        Set<Object> resultList = questions.stream().map(question -> {
            if(question instanceof MultipleChoiceAssessmentQuestion multipleChoiceQuestion){
                return mapper.multipleChoiceQuestionToDto((MultipleChoiceAssessmentQuestion) question);
            }
            throw new UnknownQuestionTypeException("Failed to parse question to a known type");
        }).collect(Collectors.toSet());
        return ResponseEntity.ok(resultList);
    }

    @Override
    public ResponseEntity<?> getQuestionsByAssessment(Long assessmentId, Pageable pageable) {
        Optional<Assessment> assessment = assessmentRepository.findById(assessmentId);
        if (assessment.isEmpty()){
            throw new InvalidRequestException("Assessment with id: " + assessmentId + " not found");
        }
        return ResponseEntity.ok(assessmentQuestionRepository.findAllByAssessment(assessment.get(), pageable));
    }

    @Override
    public ResponseEntity<?> getQuestionsByCreatorUsername(String username, Pageable pageable) {
        return ResponseEntity.ok(assessmentQuestionRepository.findAllByCreatorUsername(username, pageable));
    }

    @Override
    public ResponseEntity<?> getQuestionsByModifierUsername(String username, Pageable pageable) {
        return ResponseEntity.ok(assessmentQuestionRepository.findAllByModifierUsername(username, pageable));
    }

    @Override
    public ResponseEntity<?> createQuestion(AssessmentQuestionCreationRequest request) {
        int numberOfCorrectAnswersInRequest = (int) request.getPossibleAnswers().stream()
                .filter(answer -> answer.getIsCorrect().equals(true)).count();
        int numberOfNullAnswers = (int) request.getPossibleAnswers().stream()
                .filter(answer -> answer.getAnswerText().equals("")).count();
        if(numberOfCorrectAnswersInRequest != 1){
            throw new InvalidRequestException(
                    "Invalid number of correct answers. The system only supports 1 (one) correct answer");
        }
        if(numberOfNullAnswers > 0){
            throw new InvalidRequestException("Found an empty possible answer in request");
        }
        MultipleChoiceAssessmentQuestion question = mapper.createRequestToMultipleChoiceQuestion(request);
        return ResponseEntity.ok(assessmentQuestionRepository.save(question));
    }

    @Override
    public ResponseEntity<?> updateQuestion(AssessmentQuestionUpdateRequest request) {
        if(request.getId() == null || request.getModifierUsername().equals("") || request.getQuestion().equals("") ||
                request.getAnswerId() == null){
            throw new InvalidRequestException("None of the fields in request are nullable");
        }
        MultipleChoiceAssessmentQuestion question = mapper.updateRequestToMultipleChoiceQuestion(request);
        return ResponseEntity.ok(assessmentQuestionRepository.save(question));
    }

    @Override
    @Transactional
    public ResponseEntity<?> updatePossibleAnswer(PossibleAnswerRequest possibleAnswerRequest) {
        if(possibleAnswerRequest.getId() == null){
            throw new InvalidRequestException("PossibleAnswerId cannot be null");
        }
        if(possibleAnswerRequest.getAssociatedUsername() == null){
            throw new InvalidRequestException("Modifier username cannot be empty");
        }
        if(possibleAnswerRequest.getAnswerText() == null){
            throw new InvalidRequestException("AnswerText cannot be empty");
        }
        if(possibleAnswerRequest.getIsCorrect() == null){
            throw new InvalidRequestException("IsCorrect cannot be empty");
        }
        PossibleAnswer answer = mapper.requestToPossibleAnswerUpdate(possibleAnswerRequest);
        AssessmentQuestion question = answer.getQuestion();
        if(possibleAnswerRequest.getIsCorrect().equals(true) && question instanceof MultipleChoiceAssessmentQuestion multipleChoiceQuestion){
            multipleChoiceQuestion.setAnswer(answer);
            assessmentQuestionRepository.save(multipleChoiceQuestion);
        }
        //TODO: add other assessment question types here
        return ResponseEntity.ok(possibleAnswerRepository.save(answer));
    }

    @Override
    public ResponseEntity<?> deleteQuestion(Long questionId) {
        AssessmentQuestion questionToDelete = assessmentQuestionRepository.findById(questionId).orElse(null);
        if(questionToDelete == null){
            throw new InvalidRequestException("Question with id: " + questionId + " not found");
        }
        assessmentQuestionRepository.delete(questionToDelete);
        return ResponseEntity.ok("Question with id: " + questionId + " successfully deleted");
    }

    @Override
    public ResponseEntity<?> attemptAssessment(String username, Long assessmentId) {
        Optional<Assessment> attemptedAssessment = assessmentRepository.findById(assessmentId);
        if(attemptedAssessment.isEmpty()){
            throw new InvalidRequestException("Could not find an assessment activity with id: " + assessmentId);
        }
        AssessmentAttempt attempt = AssessmentAttempt.builder()
                .takerUsername(username)
                .attemptedTime(ZonedDateTime.now())
                .takenAssessment(attemptedAssessment.get())
                .build();
        return ResponseEntity.ok(assesssmentAttemptRepository.saveAndFlush(attempt));
    }

    @Override
    public ResponseEntity<?> attemptMultipleChoiceQuestion(Long questionId, Long assessmentAttemptId,
                                                           Long chosenAnswerId, String username) {
        if(questionId == null || chosenAnswerId == null || username == null){
            throw new InvalidRequestException("None of the requests fields is nullable");
        }
        MultipleChoiceAssessmentQuestion question = multipleChoiceQuestionRepository.findById(questionId).orElse(null);
        AssessmentAttempt assessmentAttempt = assesssmentAttemptRepository.findById(assessmentAttemptId).orElse(null);
        PossibleAnswer chosenAnswer = possibleAnswerRepository.findById(chosenAnswerId).orElse(null);
        //UserAccount user = feignClient.findUserbyUsername(username);
        if(question == null){
            throw new InvalidRequestException("Could not find Question with id: " + questionId);
        }
        if(assessmentAttempt == null){
            throw new InvalidRequestException("Could not find Assessment Attempt with id: " + assessmentAttemptId);
        }
        if(chosenAnswer == null){
            throw new InvalidRequestException("Could not find Possible Answer with id: " + chosenAnswerId);
        }
//        if(user == null){
//            throw new InvalidRequestException("Could not find User with username: " + username);
//        }
        assessmentAttempt.getAttemptedQuestions().add(question);
        AttemptedMultipleChoiceQuestion attemptedQuestion = AttemptedMultipleChoiceQuestion.builder()
                .assessmentAttempt(assessmentAttempt)
                .givenAnswer(chosenAnswer)
                .question(question)
                .build();
        assesssmentAttemptRepository.save(assessmentAttempt);
        attemptedQuestionRepository.save(attemptedQuestion);
        MultipleChoiceQuestionDto nextQuestion = getNextQuestion(assessmentAttempt);
        if (nextQuestion == null){
            return new ResponseEntity<>("No more questions in data store", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(nextQuestion);
    }

    @Override
    public ResponseEntity<?> submitAssessment(String username, Long attemptedAssessmentId) {
        AssessmentAttempt attempt = assesssmentAttemptRepository.findById(attemptedAssessmentId).orElse(null);
        if(attempt == null){
            throw new InvalidRequestException("Assessment attempt not found");
        }
        if(! attempt.getTakerUsername().equals(username)){
            throw new InvalidRequestException("Provided username does not match corresponding username in Assessment Attempt");
        }
        attempt.setIsSubmitted(true);
        return ResponseEntity.ok(assesssmentAttemptRepository.save(attempt));
    }

    private MultipleChoiceQuestionDto getNextQuestion(AssessmentAttempt assessmentAttempt){
        List<MultipleChoiceAssessmentQuestion> questionPool = multipleChoiceQuestionRepository
                .findAllByAssessment(assessmentAttempt.getTakenAssessment());
        List<AssessmentQuestion> union = new ArrayList<>(questionPool);
        union.addAll(assessmentAttempt.getAttemptedQuestions().stream().toList());
        List<AssessmentQuestion> intersection = new ArrayList<>(questionPool);
        intersection.retainAll(assessmentAttempt.getAttemptedQuestions());
        List<AssessmentQuestion> availableQuestions = new ArrayList<>(union);
        availableQuestions.removeAll(intersection);

        if(availableQuestions.isEmpty()){
            return null;
        }

        Random random = new Random();
        MultipleChoiceAssessmentQuestion nextQuestion = (MultipleChoiceAssessmentQuestion) availableQuestions.get(
                random.nextInt(availableQuestions.size()));
        return mapper.multipleChoiceQuestionToDto(nextQuestion);
    }
}
