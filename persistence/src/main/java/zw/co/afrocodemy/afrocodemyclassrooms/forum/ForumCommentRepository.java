package zw.co.afrocodemy.afrocodemyclassrooms.forum;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumCommentRepository extends JpaRepository<ForumComment, Long> {

}
