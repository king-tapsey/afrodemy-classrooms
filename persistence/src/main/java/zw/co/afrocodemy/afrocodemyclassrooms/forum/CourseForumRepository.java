package zw.co.afrocodemy.afrocodemyclassrooms.forum;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseForumRepository extends JpaRepository<CourseForum, Long> {

}
