package zw.co.afrocodemy.afrocodemyclassrooms.course;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import zw.co.afrocodemy.afrocodemyclassrooms.course.dto.CourseCreateRequest;
import zw.co.afrocodemy.afrocodemyclassrooms.course.dto.CourseUpdateRequest;

public interface CourseService {
    ResponseEntity<?> getAll(Pageable pageable);
    ResponseEntity<?> getByCourseTypes(CourseType courseType, Pageable pageable);
    ResponseEntity<?> getById(Long id);
    ResponseEntity<?> createCourse(CourseCreateRequest courseCreateRequest);
    ResponseEntity<?> updateCourse(CourseUpdateRequest courseUpdateRequest);
    ResponseEntity<?> deleteCourse(Long courseId, String username);
}
