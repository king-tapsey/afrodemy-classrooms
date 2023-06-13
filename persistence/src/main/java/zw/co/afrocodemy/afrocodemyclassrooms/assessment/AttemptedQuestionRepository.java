package zw.co.afrocodemy.afrocodemyclassrooms.assessment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AttemptedQuestionRepository extends JpaRepository<AttemptedMultipleChoiceQuestion, Long> {
}
