package zw.co.afrocodemy.afrocodemyclassrooms.forum.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import zw.co.afrocodemy.afrocodemyclassrooms.exceptions.InvalidRequestException;
import zw.co.afrocodemy.afrocodemyclassrooms.forum.*;
import zw.co.afrocodemy.afrocodemyclassrooms.forum.dto.ForumAnswerResponse;
import zw.co.afrocodemy.afrocodemyclassrooms.forum.dto.ForumQuestionRequest;

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

    @Override
    public ResponseEntity<?> getAll(Pageable pageable) {
        return ResponseEntity.ok(forumQuestionRepository.findAll(pageable));
    }

    @Override
    public ResponseEntity<?> getAllByNetVotes(Pageable pageable) {
        return null;
    }

    @Override
    public ResponseEntity<?> getById(Long id, String username) {
        Optional<ForumQuestion> question = forumQuestionRepository.findById(id);
        if(question.isEmpty()){
            throw new InvalidRequestException("Could not find ForumQuestion with id: " + id);
        }
        List<ForumAnswer> answerList = forumAnswerRepository.findByQuestion(question.get());
        List<ForumAnswerResponse> forumAnswerResponses = new ArrayList<>();
        for(ForumAnswer forumAnswer : answerList){
            Integer answerVotes = forumVotesService.getIntAnswerVotes(forumAnswer);
            Optional<ForumAnswerUserVote> vote = forumVotesService.getAnswerVoteByUsernameAndAnswer(forumAnswer, username);
            ForumVoteType voteType = ForumVoteType.NULL_VOTE;
            if(vote.isPresent()){
                voteType = vote.get().getVoteType();
            }
            List<ForumAnswerComment> comments = forumAnswerCommentRepository.findAllByAnswer(forumAnswer);

        }
        return ResponseEntity.ok(question.get());
    }

    @Override
    public ResponseEntity<?> getQuestionComments(Long questionId) {
        return null;
    }

    @Override
    public ResponseEntity<?> getQuestionAnswers(Long questionId) {
        return null;
    }

    @Override
    public ResponseEntity<?> create(ForumQuestionRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<?> update(ForumQuestionRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(Long questionId, String username) {
        return null;
    }
}
