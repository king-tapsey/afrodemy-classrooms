package zw.co.afrocodemy.afrocodemyclassrooms.forum.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Streamable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import zw.co.afrocodemy.afrocodemyclassrooms.DtoMapper;
import zw.co.afrocodemy.afrocodemyclassrooms.exceptions.InvalidRequestException;
import zw.co.afrocodemy.afrocodemyclassrooms.forum.*;
import zw.co.afrocodemy.afrocodemyclassrooms.forum.dto.ForumAnswerResponse;
import zw.co.afrocodemy.afrocodemyclassrooms.forum.dto.ForumQuestionRequest;
import zw.co.afrocodemy.afrocodemyclassrooms.forum.dto.ForumQuestionResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ForumQuestionServiceImpl implements ForumQuestionService {
    private final ForumQuestionRepository forumQuestionRepository;
    private final ForumAnswerRepository forumAnswerRepository;
    private final ForumVotesServiceImpl forumVotesService;
    private final ForumAnswerCommentRepository forumAnswerCommentRepository;
    private final ForumQuestionCommentRepository forumQuestionCommentRepository;
    private final ForumAnswerServiceImpl forumAnswerService;
    private final DtoMapper mapper;

    @Override
    public ResponseEntity<Page<ForumQuestionResponse>> getAll(Pageable pageable, String username) {
        Page<ForumQuestion> questions = forumQuestionRepository.findAll(pageable);
        List<ForumQuestionResponse> responses = questions.stream().map(question -> generateQuestionResponse(question, username)).toList();

        Page<ForumQuestionResponse> responsePage = new PageImpl<>(responses, pageable, questions.getTotalElements());

        return ResponseEntity.ok(responsePage);
    }

    @Override
    public ResponseEntity<?> getAllByNetVotes(Pageable pageable) {
        return null;
    }

    @Override
    public ResponseEntity<ForumQuestionResponse> getById(Long id, String username) {
        ForumQuestion question = getQuestion(id);
        ForumQuestionResponse response = generateQuestionResponse(question, username);
        return ResponseEntity.ok(response);
    }

    private ForumQuestionResponse generateQuestionResponse(ForumQuestion question, String username){
        Optional<ForumQuestionUserVote> questionVote = forumVotesService.getQuestionVoteByUsernameAndQuestion(username, question);
        Integer questionVotes = forumVotesService.getIntQuestionVotes(question);
        ForumVoteType questionVoteType = questionVote.isPresent() ? questionVote.get().getVoteType() : ForumVoteType.NULL_VOTE;
        List<ForumQuestionComment> questionComments = forumQuestionCommentRepository.findAllByQuestion(question);

        List<ForumAnswerResponse> forumAnswerResponses = getForumQuestionAnswers(question, username);

        return ForumQuestionResponse.builder()
                .question(question)
                .questionVotes(questionVotes)
                .userForumVoteType(questionVoteType)
                .comments(questionComments)
                .forumAnswerList(forumAnswerResponses)
                .build();
    }

    @Override
    public ResponseEntity<List<ForumQuestionComment>> getQuestionComments(Long questionId) {
        ForumQuestion question = getQuestion(questionId);
        return ResponseEntity.ok(forumQuestionCommentRepository.findAllByQuestion(question));
    }

    @Override
    public ResponseEntity<List<ForumAnswerResponse>> getQuestionAnswers(Long questionId, String username) {
        ForumQuestion question = getQuestion(questionId);
        List<ForumAnswerResponse> forumAnswerResponses = getForumQuestionAnswers(question, username);
        return ResponseEntity.ok(forumAnswerResponses);
    }

    private ForumQuestion getQuestion(Long questionId){
        Optional<ForumQuestion> question = forumQuestionRepository.findById(questionId);
        if(question.isEmpty()){
            throw new InvalidRequestException("Could not find ForumQuestion with id: " + questionId);
        }
        return question.get();
    }

    private List<ForumAnswerResponse> getForumQuestionAnswers(ForumQuestion question, String username){
        List<ForumAnswer> answerList = forumAnswerRepository.findByQuestion(question);
        List<ForumAnswerResponse> forumAnswerResponses = new ArrayList<>();
        if(! answerList.isEmpty()){
            for(ForumAnswer forumAnswer : answerList){
                ForumAnswerResponse response = forumAnswerService.generateForumAnswerResponse(forumAnswer, username);
                forumAnswerResponses.add(response);
            }
        }
        return forumAnswerResponses;
    }

    @Override
    public ResponseEntity<?> create(ForumQuestionRequest request) {
        if (request.getAssociatedUsername() == null){
            throw new InvalidRequestException("Creator Username cannot be empty.");
        }
        if(request.getText() == null){
            throw new InvalidRequestException("Question Text cannot be empty.");
        }
        ForumQuestion question = mapper.requestToForumQuestionCreate(request);
        return ResponseEntity.ok(forumQuestionRepository.save(question));
    }

    @Override
    public ResponseEntity<?> update(ForumQuestionRequest request) {
        if (request.getId() == null){
            throw new InvalidRequestException("Question Id cannot be empty.");
        }
        if(request.getText() == null){
            throw new InvalidRequestException("Question Text cannot be empty.");
        }
        ForumQuestion question = mapper.requestToForumQuestionUpdate(request);
        return ResponseEntity.ok(forumQuestionRepository.save(question));
    }

    @Override
    public ResponseEntity<?> delete(Long questionId, String username) {
        ForumQuestion question = getQuestion(questionId);
        forumQuestionRepository.delete(question);
        return ResponseEntity.ok("Successfully deleted question with id: " + question);
    }
}
