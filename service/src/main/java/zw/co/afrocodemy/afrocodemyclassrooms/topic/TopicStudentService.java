package zw.co.afrocodemy.afrocodemyclassrooms.topic;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import zw.co.afrocodemy.afrocodemyclassrooms.topic.dto.TopicStudentRequest;

public interface TopicStudentService {
    ResponseEntity<?> getAll(Pageable pageable);
    ResponseEntity<?> getAllByTopic(Long topicId, Pageable pageable);
    ResponseEntity<?> create(TopicStudentRequest request);

}
