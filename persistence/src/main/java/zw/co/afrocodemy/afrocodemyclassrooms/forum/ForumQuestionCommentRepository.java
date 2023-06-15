package zw.co.afrocodemy.afrocodemyclassrooms.forum;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ForumQuestionCommentRepository extends JpaRepository<ForumQuestionComment, Long> {
    List<ForumQuestionComment> findAllByQuestion(ForumQuestion question);
}
