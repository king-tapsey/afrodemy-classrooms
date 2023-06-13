package zw.co.afrocodemy.afrocodemyclassrooms.topic;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import zw.co.afrocodemy.afrocodemyclassrooms.topic.dto.TopicRequest;

public interface TopicService {
    ResponseEntity<?> getAll(Pageable pageable);
    ResponseEntity<?> getAllByCourseId(Long courseId);
    ResponseEntity<?> create(TopicRequest request);
    ResponseEntity<?> update(TopicRequest request);
    ResponseEntity<?> delete(Long topicId);
}
