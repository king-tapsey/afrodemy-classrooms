package zw.co.afrocodemy.afrocodemyclassrooms.assessment;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssessmentAttempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String takerUsername;
    protected ZonedDateTime attemptedTime;
    protected Boolean isSubmitted;
    @ManyToOne
    protected Assessment takenAssessment;
    @OneToMany
    protected Set<AssessmentQuestion> attemptedQuestions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AssessmentAttempt that = (AssessmentAttempt) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(takerUsername, that.takerUsername))
            return false;
        if (!Objects.equals(attemptedTime, that.attemptedTime))
            return false;
        if (!Objects.equals(takenAssessment, that.takenAssessment))
            return false;
        return Objects.equals(attemptedQuestions, that.attemptedQuestions);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (takerUsername != null ? takerUsername.hashCode() : 0);
        result = 31 * result + (attemptedTime != null ? attemptedTime.hashCode() : 0);
        result = 31 * result + (takenAssessment != null ? takenAssessment.hashCode() : 0);
        result = 31 * result + (attemptedQuestions != null ? attemptedQuestions.hashCode() : 0);
        return result;
    }
}
