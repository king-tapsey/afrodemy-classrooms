package zw.co.afrocodemy.afrocodemyclassrooms.assessment;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttemptedMultipleChoiceQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @ManyToOne
    AssessmentAttempt assessmentAttempt;
    @ManyToOne
    MultipleChoiceAssessmentQuestion question;
    @ManyToOne
    PossibleAnswer givenAnswer;

    public Boolean isCorrect(){
        return question.getAnswer().equals(givenAnswer);
    }

    @Override
    public String toString() {
        return "AttemptedQuestion{" +
                "id=" + id +
                ", assessmentAttemptId=" + assessmentAttempt.getId() +
                ", question=" + question.getQuestion() +
                ", givenAnswer=" + givenAnswer.getAnswerText() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AttemptedMultipleChoiceQuestion that = (AttemptedMultipleChoiceQuestion) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(assessmentAttempt, that.assessmentAttempt)) return false;
        if (!Objects.equals(question, that.question)) return false;
        return Objects.equals(givenAnswer, that.givenAnswer);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (assessmentAttempt != null ? assessmentAttempt.hashCode() : 0);
        result = 31 * result + (question != null ? question.hashCode() : 0);
        result = 31 * result + (givenAnswer != null ? givenAnswer.hashCode() : 0);
        return result;
    }
}
