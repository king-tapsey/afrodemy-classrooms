package zw.co.afrocodemy.afrocodemyclassrooms.forum;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ForumAnswerCommentRepository extends JpaRepository<ForumAnswerComment, Long> {
    List<ForumAnswerComment> findAllByAnswer(ForumAnswer answer);
}