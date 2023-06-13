package zw.co.afrocodemy.afrocodemyclassrooms.topic;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import zw.co.afrocodemy.afrocodemyclassrooms.topic.dto.TopicRequest;

public interface TopicMaterialService {
    ResponseEntity<?> getAll(Pageable pageable);
    ResponseEntity<?> getAllByTopic(Long topicId);
    ResponseEntity<?> create(TopicRequest request);
    ResponseEntity<?> update(TopicRequest request);
    ResponseEntity<?> delete(Long topicMaterialId);
}
