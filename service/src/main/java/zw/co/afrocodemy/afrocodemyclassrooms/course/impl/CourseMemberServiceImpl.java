package zw.co.afrocodemy.afrocodemyclassrooms.course.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import zw.co.afrocodemy.afrocodemyclassrooms.course.CourseMember;
import zw.co.afrocodemy.afrocodemyclassrooms.course.CourseMemberProgress;
import zw.co.afrocodemy.afrocodemyclassrooms.course.CourseMemberRepository;
import zw.co.afrocodemy.afrocodemyclassrooms.course.CourseMemberService;
import zw.co.afrocodemy.afrocodemyclassrooms.exceptions.CourseMemberCreationException;
import zw.co.afrocodemy.afrocodemyclassrooms.exceptions.InvalidRequestException;

import java.util.Optional;

@RequiredArgsConstructor
public class CourseMemberServiceImpl implements CourseMemberService {
    private final CourseMemberRepository courseMemberRepository;

    @Override
    public ResponseEntity<?> getAll(Pageable pageable) {
            return ResponseEntity.ok(courseMemberRepository.findAll(pageable));
    }

    @Override
    public ResponseEntity<?> getAllByCourseMemberProgress(CourseMemberProgress courseMemberProgress, Pageable pageable) {
        return ResponseEntity.ok(courseMemberRepository.findAllByCourseMemberProgress(courseMemberProgress, pageable));
    }

    @Override
    public ResponseEntity<?> getById(Long id) {
        Optional<CourseMember> courseMember = courseMemberRepository.findById(id);
        if(courseMember.isEmpty()){
            throw new InvalidRequestException("CourseMember with id: " + id + " not found");
        }
        return ResponseEntity.ok(courseMember.get());
    }

    @Override
    public ResponseEntity<?> create(CourseMember courseMember) {
        if(courseMemberRepository.existsByCourseAndTakerUsername(courseMember.getCourse(), courseMember.getTakerUsername())){
            throw new CourseMemberCreationException("Course Member with username: " + courseMember.getTakerUsername() +
                    " already exists");
        }
        return ResponseEntity.ok(courseMemberRepository.saveAndFlush(courseMember));
    }

//    @Override
//    public ResponseEntity<?> update(CourseMember courseMember) {
//        CourseMember memberToUpdate = courseMemberRepository.findById(courseMember.getId()).orElse(null);
//        if(memberToUpdate == null){
//            throw new InvalidRequestException("Provided Course Member does not exist");
//        }
//        return null;
//    }

    @Override
    public ResponseEntity<?> delete(Long id, String username) {
        CourseMember memberToDelete = courseMemberRepository.findById(id).orElse(null);
        if(memberToDelete == null){
            throw new InvalidRequestException("Provided Course Member does not exist");
        }
        if(! memberToDelete.getTakerUsername().equals(username)){
            throw new InvalidRequestException("Provided username does not match the one in datastore");
        }
        courseMemberRepository.delete(memberToDelete);
        return ResponseEntity.ok("Successfully deleted Course Member pf id: " + id);
    }
}
