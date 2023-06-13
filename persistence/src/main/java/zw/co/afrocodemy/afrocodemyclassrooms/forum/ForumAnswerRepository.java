package zw.co.afrocodemy.afrocodemyclassrooms.forum;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumAnswerRepository extends JpaRepository<ForumAnswer, Long> {
}
