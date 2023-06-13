package zw.co.afrocodemy.afrocodemyclassrooms.forum;

import org.springframework.http.ResponseEntity;
import zw.co.afrocodemy.afrocodemyclassrooms.forum.dto.ForumVoteRequest;

import java.util.Optional;

public interface ForumVotesService {
    ResponseEntity<?> getQuestionVotes(ForumQuestion question);
    ResponseEntity<?> getAnswerVotes(ForumAnswer answer);
    Optional<ForumAnswerUserVote> getAnswerVoteByUsernameAndAnswer(String username, ForumAnswer answer);
    Optional<ForumQuestionUserVote> getQuestionVoteByUsernameAndQuestion(String username, ForumQuestion question);
    ResponseEntity<?> create(ForumVoteRequest request);
}
