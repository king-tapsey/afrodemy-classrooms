package zw.co.afrocodemy.afrocodemyclassrooms.forum;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import zw.co.afrocodemy.afrocodemyclassrooms.forum.dto.ForumQuestionRequest;

public interface ForumAnswerService {
    ResponseEntity<?> getAll(Pageable pageable);
    ResponseEntity<?> getAllByNetVotes(Pageable pageable);
    ResponseEntity<?> getById(Long id);
    ResponseEntity<?> getAnswerComments(Long answerId);
    ResponseEntity<?> create(ForumQuestionRequest request);
    ResponseEntity<?> update(ForumQuestionRequest request);
    ResponseEntity<?> delete(Long questionId, String username);
}
