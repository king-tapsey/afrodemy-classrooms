package zw.co.afrocodemy.afrocodemyclassrooms.forum.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import zw.co.afrocodemy.afrocodemyclassrooms.DtoMapper;
import zw.co.afrocodemy.afrocodemyclassrooms.exceptions.InvalidRequestException;
import zw.co.afrocodemy.afrocodemyclassrooms.forum.*;
import zw.co.afrocodemy.afrocodemyclassrooms.forum.dto.ForumVoteRequest;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ForumVotesServiceImpl implements ForumVotesService {
    private final ForumUserVoteRepository forumUserVoteRepository;
    private final ForumQuestionUserVoteRepository forumQuestionUserVoteRepository;
    private final ForumAnswerUserVoteRepository forumAnswerUserVoteRepository;
    private final DtoMapper mapper;

    @Override
    public ResponseEntity<?> getQuestionVotes(ForumQuestion question) {
        Integer upVotes = forumQuestionUserVoteRepository.countAllByVoteTypeAndQuestion(ForumVoteType.UPVOTE, question);
        Integer downVotes = forumQuestionUserVoteRepository.countAllByVoteTypeAndQuestion(ForumVoteType.DOWN_VOTE, question);
        return ResponseEntity.ok(upVotes - downVotes);
    }

    @Override
    public ResponseEntity<?> getAnswerVotes(ForumAnswer answer) {
        Integer upVotes = forumAnswerUserVoteRepository.countAllByVoteTypeAndAnswer(ForumVoteType.UPVOTE, answer);
        Integer downVotes = forumAnswerUserVoteRepository.countAllByVoteTypeAndAnswer(ForumVoteType.DOWN_VOTE, answer);
        return ResponseEntity.ok(upVotes - downVotes);
    }

    public Integer getIntAnswerVotes(ForumAnswer answer) {
        Integer upVotes = forumAnswerUserVoteRepository.countAllByVoteTypeAndAnswer(ForumVoteType.UPVOTE, answer);
        Integer downVotes = forumAnswerUserVoteRepository.countAllByVoteTypeAndAnswer(ForumVoteType.DOWN_VOTE, answer);
        return upVotes - downVotes;
    }

    @Override
    public ResponseEntity<?> create(ForumVoteRequest request) {
        if(request.getVoterUsername() == null){
            throw new InvalidRequestException("Voter Username cannot be empty");
        }
        if(request.getVoteType() == null){
            throw new InvalidRequestException("Vote Type cannot be empty");
        }
        if(request.getQuestionId() == null ^ request.getAnswerId() == null){
            throw new InvalidRequestException("QuestionId and AnswerId cannot be both null or non-null.");
        }
        if(request.getVoteType().equals(ForumVoteType.NULL_VOTE)){
            if(! forumUserVoteRepository.existsByVoterUsername(request.getVoterUsername())){
                return ResponseEntity.ok(null);
            }
            ForumUserVote vote = forumUserVoteRepository.findByVoterUsername(request.getVoterUsername()).get();
            forumUserVoteRepository.delete(vote);
            return ResponseEntity.ok("Success!");
        }
        if(request.getVoteType().equals(ForumVoteType.DOWN_VOTE)){
            return addNewVote(request);
        }
        return addNewVote(request);
    }

    private ResponseEntity<?> addNewVote(ForumVoteRequest request){
        Optional<ForumUserVote> vote = forumUserVoteRepository.findByVoterUsername(request.getVoterUsername());
        if(vote.isPresent()){
            vote.get().setVoteType(request.getVoteType());
            return ResponseEntity.ok(forumUserVoteRepository.saveAndFlush(vote.get()));
        }
        if(request.getQuestionId() != null){
            ForumUserVote newVote = mapper.requestToForumQuestionVote(request);
            return ResponseEntity.ok(forumUserVoteRepository.saveAndFlush(newVote));
        }
        ForumUserVote newVote = mapper.requestToForumAnswerVote(request);
        return ResponseEntity.ok(forumUserVoteRepository.saveAndFlush(newVote));
    }
    public Optional<ForumAnswerUserVote> getAnswerVoteByUsernameAndAnswer(String username, ForumAnswer answer){
        return forumAnswerUserVoteRepository.findByAnswerAndVoterUsername(answer, username);
    }
    public Optional<ForumQuestionUserVote> getQuestionVoteByUsernameAndQuestion(String username, ForumQuestion question){
        return forumQuestionUserVoteRepository.findByQuestionAndVoterUsername(question, username);
    }
}
