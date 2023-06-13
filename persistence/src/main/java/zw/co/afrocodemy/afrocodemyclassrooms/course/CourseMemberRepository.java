package zw.co.afrocodemy.afrocodemyclassrooms.course;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseMemberRepository extends JpaRepository<CourseMember, Long> {
    List<CourseMember> findAllByCourseMemberProgress(CourseMemberProgress courseMemberProgress, Pageable pageable);
    Boolean existsByCourseAndTakerUsername(Course course, String takerUsername);
}
