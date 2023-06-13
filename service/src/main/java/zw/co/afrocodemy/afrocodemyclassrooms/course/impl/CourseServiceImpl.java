package zw.co.afrocodemy.afrocodemyclassrooms.course.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import zw.co.afrocodemy.afrocodemyclassrooms.DtoMapper;
import zw.co.afrocodemy.afrocodemyclassrooms.course.Course;
import zw.co.afrocodemy.afrocodemyclassrooms.course.CourseRepository;
import zw.co.afrocodemy.afrocodemyclassrooms.course.CourseService;
import zw.co.afrocodemy.afrocodemyclassrooms.course.CourseType;
import zw.co.afrocodemy.afrocodemyclassrooms.course.dto.CourseCreateRequest;
import zw.co.afrocodemy.afrocodemyclassrooms.course.dto.CourseUpdateRequest;
import zw.co.afrocodemy.afrocodemyclassrooms.exceptions.CourseCreationException;
import zw.co.afrocodemy.afrocodemyclassrooms.exceptions.CourseNotFoundException;
import zw.co.afrocodemy.afrocodemyclassrooms.exceptions.InvalidRequestException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final DtoMapper mapper;

    @Override
    public ResponseEntity<?> getAll(Pageable pageable) {
        return ResponseEntity.ok(courseRepository.findAll(pageable));
    }

    @Override
    public ResponseEntity<?> getByCourseTypes(CourseType courseType, Pageable pageable) {
        return ResponseEntity.ok(courseRepository.findAllByCourseType(courseType, pageable));
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        if(course.isEmpty()){
            throw new CourseNotFoundException(id);
        }
        return ResponseEntity.ok(course.get());
    }

    @Override
    public ResponseEntity<?> createCourse(CourseCreateRequest request) {
        if(request.getCreatorUsername() == null || request.getTitle() == null || request.getCourseType() == null){
            throw new CourseCreationException("Creator Username, Title, or Course Type cannot be null.");
        }
        Course course = mapper.requestToCourseCreate(request);
        return ResponseEntity.ok(courseRepository.saveAndFlush(course));
    }

    @Override
    public ResponseEntity<?> updateCourse(CourseUpdateRequest request) {
        if(request.getModifierUsername() == null){
            throw new InvalidRequestException("Modifier username cannot be empty.");
        }
        if(request.getId() == null){
            throw new InvalidRequestException("Course Id cannot be null.");
        }
        Course course = mapper.requestToCourseUpdate(request);
        return ResponseEntity.ok(courseRepository.saveAndFlush(course));
    }

    @Override
    public ResponseEntity<?> deleteCourse(Long courseId, String username) {
        Course course = courseRepository.findById(courseId).orElse(null);
        if(course == null){
            throw new CourseNotFoundException("Could not find course with id: " + courseId);
        }
        courseRepository.delete(course);
        return ResponseEntity.ok("Successful deletion of Course with id: " + courseId);
    }
}
