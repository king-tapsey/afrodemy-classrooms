package zw.co.afrocodemy.afrocodemyclassrooms.assessment;

import jakarta.persistence.*;
import lombok.*;
import zw.co.afrocodemy.afrocodemyclassrooms.BaseEntity;

import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class AssessmentQuestion extends BaseEntity {
    protected ZonedDateTime modifiedDate;
    protected String modifierUsername;
    protected String question;
    @ManyToOne
    protected Assessment assessment;
    @Enumerated(EnumType.STRING)
    protected AssessmentType assessmentType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        AssessmentQuestion question1 = (AssessmentQuestion) o;

        if (!Objects.equals(modifiedDate, question1.modifiedDate))
            return false;
        if (!Objects.equals(modifierUsername, question1.modifierUsername))
            return false;
        if (!Objects.equals(question, question1.question)) return false;
        if (!Objects.equals(assessment, question1.assessment)) return false;
        return assessmentType == question1.assessmentType;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (modifiedDate != null ? modifiedDate.hashCode() : 0);
        result = 31 * result + (modifierUsername != null ? modifierUsername.hashCode() : 0);
        result = 31 * result + (question != null ? question.hashCode() : 0);
        result = 31 * result + (assessment != null ? assessment.hashCode() : 0);
        result = 31 * result + (assessmentType != null ? assessmentType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AssessmentQuestion{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", assessmentId=" + assessment.getId() +
                ", assessmentType=" + assessmentType +
                '}';
    }
}
