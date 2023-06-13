package zw.co.afrocodemy.afrocodemyclassrooms.course.impl;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import zw.co.afrocodemy.afrocodemyclassrooms.course.CourseService;
import zw.co.afrocodemy.afrocodemyclassrooms.course.CourseType;
import zw.co.afrocodemy.afrocodemyclassrooms.course.dto.CourseCreateRequest;
import zw.co.afrocodemy.afrocodemyclassrooms.course.dto.CourseUpdateRequest;

public class CourseServiceImpl implements CourseService {
    @Override
    public ResponseEntity<?> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public ResponseEntity<?> getByCourseTypes(CourseType courseType, Pageable pageable) {
        return null;
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> createCourse(CourseCreateRequest courseCreateRequest) {
        return null;
    }

    @Override
    public ResponseEntity<?> updateCourse(CourseUpdateRequest courseUpdateRequest) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteCourse(Long courseId, String username) {
        return null;
    }
}
