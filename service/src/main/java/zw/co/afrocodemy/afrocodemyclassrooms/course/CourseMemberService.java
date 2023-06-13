package zw.co.afrocodemy.afrocodemyclassrooms.course;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CourseMemberService {
    ResponseEntity<?> getAll(Pageable pageable);
    ResponseEntity<?> getAllByCourseMemberProgress(CourseMemberProgress courseMemberProgress, Pageable pageable);
    ResponseEntity<?> getById(Long id);
    ResponseEntity<?> create(CourseMember courseMember);
//    ResponseEntity<?> update(CourseMember courseMember);
    ResponseEntity<?> delete(Long id, String username);

}
