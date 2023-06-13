package zw.co.afrocodemy.afrocodemyclassrooms.assessment;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import lombok.*;
import zw.co.afrocodemy.afrocodemyclassrooms.BaseEntity;

import java.time.Period;
import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Assessment extends BaseEntity {
    protected ZonedDateTime modifiedDate;
    protected String modifierUsername;
    protected String name;
    @ManyToOne
    protected Assessment precededBy;
    protected Integer numberOfQuestions;
    protected Period allowedAttemptTime;
    protected Period timeBeforeRetake;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Assessment that = (Assessment) o;

        if (!Objects.equals(modifiedDate, that.modifiedDate)) return false;
        if (!Objects.equals(modifierUsername, that.modifierUsername)) return false;
        if (!Objects.equals(name, that.name)) return false;
        if (!Objects.equals(numberOfQuestions, that.numberOfQuestions)) return false;
        if (!Objects.equals(allowedAttemptTime, that.allowedAttemptTime)) return false;
        return Objects.equals(timeBeforeRetake, that.timeBeforeRetake);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (modifiedDate != null ? modifiedDate.hashCode() : 0);
        result = 31 * result + (modifierUsername != null ? modifierUsername.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (numberOfQuestions != null ? numberOfQuestions.hashCode() : 0);
        result = 31 * result + (allowedAttemptTime != null ? allowedAttemptTime.hashCode() : 0);
        result = 31 * result + (timeBeforeRetake != null ? timeBeforeRetake.hashCode() : 0);
        return result;
    }
}
