package zw.co.afrocodemy.afrocodemyclassrooms;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zw.co.afrocodemy.afrocodemyclassrooms.assessment.*;
import zw.co.afrocodemy.afrocodemyclassrooms.assessment.dto.*;
import zw.co.afrocodemy.afrocodemyclassrooms.course.Course;
import zw.co.afrocodemy.afrocodemyclassrooms.course.CourseRepository;
import zw.co.afrocodemy.afrocodemyclassrooms.course.dto.CourseCreateRequest;
import zw.co.afrocodemy.afrocodemyclassrooms.course.dto.CourseUpdateRequest;
import zw.co.afrocodemy.afrocodemyclassrooms.exceptions.CourseNotFoundException;
import zw.co.afrocodemy.afrocodemyclassrooms.exceptions.InvalidRequestException;
import zw.co.afrocodemy.afrocodemyclassrooms.forum.*;
import zw.co.afrocodemy.afrocodemyclassrooms.forum.dto.*;

import java.time.ZonedDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DtoMapper {
    private final AssessmentRepository assessmentRepository;
    private final MultipleChoiceQuestionRepository multipleChoiceRepository;
    private final PossibleAnswerRepository possibleAnswerRepository;
    private final CourseRepository courseRepository;
    private final CourseForumRepository courseForumRepository;
    private final ForumAnswerRepository forumAnswerRepository;
    private final ForumQuestionRepository forumQuestionRepository;
    private final ForumAnswerCommentRepository forumAnswerCommentRepository;
    private final ForumQuestionCommentRepository forumQuestionCommentRepository;
    private final ForumCommentRepository forumCommentRepository;

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
        final PossibleAnswer answer = possibleAnswerRepository.findById(request.getId()).orElse(null);
        if(answer == null){
            throw new InvalidRequestException("Possible answer with Id: " + request.getId() + " not found");
        }
        answer.setModifiedDate(ZonedDateTime.now());
        answer.setModifierUsername(request.getAssociatedUsername());
        answer.setAnswerText(request.getAnswerText());

        return answer;
    }
    public Course requestToCourseCreate(final CourseCreateRequest request){
        final Course course = new Course();

        course.setCreatedDate(ZonedDateTime.now());
        course.setCreatorUsername(request.getCreatorUsername());
        course.setTitle(request.getTitle());
        course.setSubTitle(request.getSubTitle());
        course.setDetails(request.getDetails());
        course.setCourseType(request.getCourseType());

        return course;
    }
    public Course requestToCourseUpdate(final CourseUpdateRequest request){
        final Course course = courseRepository.findById(request.getId()).orElse(null);
        if(course == null){
            throw new CourseNotFoundException("Could not find course with id: " + request.getId() + " included in the Course UpdateRequest.");
        }

        course.setModifiedDate(ZonedDateTime.now());
        course.setModifierUsername(request.getModifierUsername());
        course.setTitle(request.getTitle() != null ? request.getTitle() : course.getTitle());
        course.setSubTitle(request.getSubTitle() != null ? request.getSubTitle() : course.getSubTitle());
        course.setCourseType(request.getCourseType() != null ? request.getCourseType() : course.getCourseType());

        return course;
    }
    public CourseForum requestToForumCreate(final ForumRequest request){
        final CourseForum forum  = new CourseForum();
        final Course course = courseRepository.findById(request.getCourseId()).orElse(null);
        if(course == null){
            throw new InvalidRequestException("Could not find Course with Id: " + request.getCourseId());
        }

        forum.setCreatedDate(ZonedDateTime.now());
        forum.setCreatorUsername(request.getAssociatedUsername());
        forum.setName(request.getName());
        forum.setCourse(course);

        return forum;
    }
    public CourseForum requestToForumUpdate(final ForumRequest request){
        final CourseForum forum = courseForumRepository.findById(request.getId()).orElse(null);
        if(forum == null){
            throw new InvalidRequestException("Could not find forum with id: " + request.getId());
        }

        forum.setModifiedDate(ZonedDateTime.now());
        forum.setModifierUsername(request.getAssociatedUsername());
        forum.setName(request.getName());

        return forum;
    }
    public ForumAnswerUserVote requestToForumAnswerVote(final ForumVoteRequest request){
        final ForumAnswerUserVote vote = new ForumAnswerUserVote();
        final ForumAnswer answer = forumAnswerRepository.findById(request.getAnswerId()).orElse(null);
        if(answer == null){
            throw new InvalidRequestException("Could not find ForumAnswer with id: " + request.getAnswerId());
        }

        vote.setVoterUsername(request.getVoterUsername());
        vote.setVoteType(request.getVoteType());
        vote.setAnswer(answer);

        return vote;
    }
    public ForumQuestionUserVote requestToForumQuestionVote(final ForumVoteRequest request){
        final ForumQuestionUserVote vote = new ForumQuestionUserVote();
        final ForumQuestion question = forumQuestionRepository.findById(request.getQuestionId()).orElse(null);
        if(question == null){
            throw new InvalidRequestException("Could not find ForumQuestion with id: " + request.getQuestionId());
        }

        vote.setVoterUsername(request.getVoterUsername());
        vote.setVoteType(request.getVoteType());
        vote.setQuestion(question);

        return vote;
    }
    public ForumQuestion requestToForumQuestionCreate(final ForumQuestionRequest request){
        final ForumQuestion question = new ForumQuestion();

        question.setCreatedDate(ZonedDateTime.now());
        question.setCreatorUsername(request.getAssociatedUsername());
        question.setText(request.getText());

        return question;
    }
    public ForumQuestion requestToForumQuestionUpdate(final ForumQuestionRequest request){
        final ForumQuestion question = forumQuestionRepository.findById(request.getId()).orElse(null);
        if(question == null){
            throw new InvalidRequestException("Could not find ForumQuestion with id: " + request.getId());
        }

        question.setModifiedDate(ZonedDateTime.now());
        question.setText(request.getText());

        return question;
    }
    public ForumAnswer requestToForumAnswerCreate(final ForumAnswerRequest request){
        final ForumAnswer answer = new ForumAnswer();
        final ForumQuestion question = forumQuestionRepository.findById(request.getQuestionId()).orElse(null);
        if(question == null){
            throw new InvalidRequestException("Could not find ForumQuestion with id: " + request.getQuestionId());
        }

        answer.setCreatedDate(ZonedDateTime.now());
        answer.setCreatorUsername(request.getAssociatedUsername());
        answer.setText(request.getText());
        answer.setQuestion(question);

        return answer;
    }
    public ForumAnswer requestToForumAnswerUpdate(final ForumAnswerRequest request){
        final ForumAnswer answer = forumAnswerRepository.findById(request.getId()).orElse(null);
        if(answer == null){
            throw new InvalidRequestException("Could not find ForumAnswer with id: " + request.getId());
        }

        answer.setModifiedDate(ZonedDateTime.now());
        answer.setText(request.getText());

        return answer;
    }
    public ForumAnswerComment requestToForumAnswerCommentCreate(final ForumCommentRequest request){
        final ForumAnswerComment comment = new ForumAnswerComment();
        final ForumAnswer answer = forumAnswerRepository.findById(request.getAnswerId()).orElse(null);
        if(answer == null){
            throw new InvalidRequestException("Could not find ForumAnswer with id: " + request.getAnswerId());
        }

        comment.setCreatedDate(ZonedDateTime.now());
        comment.setCreatorUsername(request.getAssociatedUsername());
        comment.setCommentText(request.getText());
        comment.setAnswer(answer);

        return comment;
    }
    public ForumComment requestToForumCommentUpdate(final ForumCommentRequest request){
        final ForumComment comment = forumCommentRepository.findById(request.getId()).orElse(null);
        if(comment == null){
            throw new InvalidRequestException("Could not find Comment with id: " + request.getId());
        }

        comment.setModifiedDate(ZonedDateTime.now());
        comment.setCommentText(request.getText());

        return comment;
    }
    public ForumQuestionComment requestToForumQuestionCommentCreate(final ForumCommentRequest request){
        final ForumQuestionComment comment = new ForumQuestionComment();
        final ForumQuestion question = forumQuestionRepository.findById(request.getQuestionId()).orElse(null);
        if(question == null){
            throw new InvalidRequestException("Could not find ForumQuestion with id: " + request.getQuestionId());
        }

        comment.setCreatedDate(ZonedDateTime.now());
        comment.setCreatorUsername(request.getAssociatedUsername());
        comment.setCommentText(request.getText());
        comment.setQuestion(question);

        return comment;
    }

}
