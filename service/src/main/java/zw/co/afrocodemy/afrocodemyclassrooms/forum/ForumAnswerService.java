package zw.co.afrocodemy.afrocodemyclassrooms.forum;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import zw.co.afrocodemy.afrocodemyclassrooms.forum.dto.ForumAnswerRequest;

public interface ForumAnswerService {
    ResponseEntity<?> getAll(Pageable pageable, String username);
    ResponseEntity<?> getAllByNetVotes(Pageable pageable);
    ResponseEntity<?> getById(Long id, String username);
    ResponseEntity<?> getAnswerComments(Long answerId);
    ResponseEntity<?> create(ForumAnswerRequest request);
    ResponseEntity<?> update(ForumAnswerRequest request);
    ResponseEntity<?> delete(Long questionId, String username);
}
