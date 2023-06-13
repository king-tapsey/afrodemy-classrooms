package zw.co.afrocodemy.afrocodemyclassrooms.forum;

import org.springframework.http.ResponseEntity;
import zw.co.afrocodemy.afrocodemyclassrooms.forum.dto.ForumVoteRequest;

public interface ForumVotesService {
    ResponseEntity<?> getQuestionVotes(ForumQuestion question);
    ResponseEntity<?> getAnswerVotes(ForumAnswer answer);
    ResponseEntity<?> create(ForumVoteRequest request);
    ResponseEntity<?> update(ForumVoteRequest request);
}
