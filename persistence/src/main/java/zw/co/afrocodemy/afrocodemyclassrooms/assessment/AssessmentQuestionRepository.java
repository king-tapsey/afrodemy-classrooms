package zw.co.afrocodemy.afrocodemyclassrooms.assessment;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssessmentQuestionRepository extends JpaRepository<AssessmentQuestion, Long> {
    List<AssessmentQuestion> findAllByAssessment(Assessment assessment, Pageable pageable);
    List<AssessmentQuestion> findAllByCreatorUsername(String creatorUsername, Pageable pageable);
    List<AssessmentQuestion> findAllByModifierUsername(String modifierUsername, Pageable pageable);
}
