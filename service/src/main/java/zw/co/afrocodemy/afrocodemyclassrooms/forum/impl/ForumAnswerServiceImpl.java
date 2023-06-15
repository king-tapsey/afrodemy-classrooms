package zw.co.afrocodemy.afrocodemyclassrooms.forum.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import zw.co.afrocodemy.afrocodemyclassrooms.DtoMapper;
import zw.co.afrocodemy.afrocodemyclassrooms.exceptions.InvalidRequestException;
import zw.co.afrocodemy.afrocodemyclassrooms.forum.*;
import zw.co.afrocodemy.afrocodemyclassrooms.forum.dto.ForumAnswerRequest;
import zw.co.afrocodemy.afrocodemyclassrooms.forum.dto.ForumAnswerResponse;
import zw.co.afrocodemy.afrocodemyclassrooms.forum.dto.ForumQuestionRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ForumAnswerServiceImpl implements ForumAnswerService {
    private final ForumAnswerRepository forumAnswerRepository;
    private final ForumVotesServiceImpl forumVotesService;
    private final ForumAnswerCommentRepository forumAnswerCommentRepository;
    private final DtoMapper mapper;

    @Override
    public ResponseEntity<?> getAll(Pageable pageable, String username) {
        Page<ForumAnswer> answerList = forumAnswerRepository.findAll(pageable);
        List<ForumAnswerResponse> forumAnswerResponses = new ArrayList<>();
        if(! answerList.isEmpty()){
            for(ForumAnswer forumAnswer : answerList){
                ForumAnswerResponse response = generateForumAnswerResponse(forumAnswer, username);
                forumAnswerResponses.add(response);
            }
            Page<ForumAnswerResponse> response = new PageImpl<>(forumAnswerResponses, pageable, answerList.getTotalElements());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.ok(null);
    }

    ForumAnswerResponse generateForumAnswerResponse(ForumAnswer forumAnswer, String username){
        Integer answerVotes = forumVotesService.getIntAnswerVotes(forumAnswer);
        Optional<ForumAnswerUserVote> answerVote = forumVotesService.getAnswerVoteByUsernameAndAnswer(username, forumAnswer);
        ForumVoteType answerVoteType = answerVote.isPresent() ? answerVote.get().getVoteType() : ForumVoteType.NULL_VOTE;
        List<ForumAnswerComment> answerComments = forumAnswerCommentRepository.findAllByAnswer(forumAnswer);

        return ForumAnswerResponse.builder()
                .answer(forumAnswer)
                .answerVotes(answerVotes)
                .userForumVoteType(answerVoteType)
                .comments(answerComments)
                .build();
    }

    @Override
    public ResponseEntity<?> getAllByNetVotes(Pageable pageable) {
        return null;
    }

    @Override
    public ResponseEntity<?> getById(Long answerId, String username) {
        ForumAnswer answer = getAnswer(answerId);
        ForumAnswerResponse response = generateForumAnswerResponse(answer, username);
        return ResponseEntity.ok(response);
    }

    private ForumAnswer getAnswer(Long answerId){
        ForumAnswer answer = forumAnswerRepository.findById(answerId).orElse(null);
        if(answer == null){
            throw new InvalidRequestException("Could not find Forum Answer with id: " + answerId);
        }
        return answer;
    }

    @Override
    public ResponseEntity<List<ForumAnswerComment>> getAnswerComments(Long answerId) {
        ForumAnswer answer = getAnswer(answerId);
        List<ForumAnswerComment> comments = forumAnswerCommentRepository.findAllByAnswer(answer);
        return ResponseEntity.ok(comments);
    }

    @Override
    public ResponseEntity<ForumAnswer> create(ForumAnswerRequest request) {
        if(request.getAssociatedUsername() == null){
            throw new InvalidRequestException("Creator username cannot be null");
        }
        if(request.getText() == null){
            throw new InvalidRequestException("Comment text cannot be null");
        }
        ForumAnswer answer = mapper.requestToForumAnswerCreate(request);
        return ResponseEntity.ok(forumAnswerRepository.save(answer));
    }

    @Override
    public ResponseEntity<?> update(ForumAnswerRequest request) {
        if(request.getId() == null){
            throw new InvalidRequestException("Answer id cannot be null");
        }
        if(request.getAssociatedUsername() == null){
            throw new InvalidRequestException("Modifier username cannot be null");
        }
        if(request.getText() == null){
            throw new InvalidRequestException("Comment text cannot be null");
        }
        ForumAnswer answer = mapper.requestToForumAnswerUpdate(request);
        return ResponseEntity.ok(answer);
    }

    @Override
    public ResponseEntity<?> delete(Long answerId, String username) {
        ForumAnswer answer = getAnswer(answerId);
        forumAnswerRepository.delete(answer);
        return ResponseEntity.ok("Successful deletion of Forum Answer with id: " + answerId);
    }
}
