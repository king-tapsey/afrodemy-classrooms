package zw.co.afrocodemy.afrocodemyclassrooms.assessment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MultipleChoiceQuestionRepository extends JpaRepository<MultipleChoiceAssessmentQuestion, Long> {
    List<MultipleChoiceAssessmentQuestion> findAllByAssessment(Assessment assessment);
}
