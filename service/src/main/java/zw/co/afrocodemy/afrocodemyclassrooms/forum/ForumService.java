package zw.co.afrocodemy.afrocodemyclassrooms.forum;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import zw.co.afrocodemy.afrocodemyclassrooms.forum.dto.ForumRequest;

public interface ForumService {
    ResponseEntity<?> getAll(Pageable pageable);
    ResponseEntity<?> getById(Long id);
    ResponseEntity<?> create(ForumRequest request);
    ResponseEntity<?> update(ForumRequest request);
    ResponseEntity<?> delete(Long forumId, String username);
}
