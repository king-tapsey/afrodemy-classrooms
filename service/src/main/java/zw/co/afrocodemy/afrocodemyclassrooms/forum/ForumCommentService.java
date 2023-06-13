package zw.co.afrocodemy.afrocodemyclassrooms.forum;

import org.springframework.http.ResponseEntity;
import zw.co.afrocodemy.afrocodemyclassrooms.forum.dto.ForumCommentRequest;

public interface ForumCommentService {
    ResponseEntity<?> getQuestionComments(Long questionId);
    ResponseEntity<?> getAnswerComments(Long answerId);
    ResponseEntity<?> getAllByUserAccount(String username);
    ResponseEntity<?> create(ForumCommentRequest request);
    ResponseEntity<?> update(ForumCommentRequest request);
    ResponseEntity<?> delete(Long id, String username);
}
