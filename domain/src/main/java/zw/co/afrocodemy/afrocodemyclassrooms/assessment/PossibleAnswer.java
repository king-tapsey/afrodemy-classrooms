package zw.co.afrocodemy.afrocodemyclassrooms.assessment;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PossibleAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected ZonedDateTime createdDate;
    protected String creatorUsername;
    protected ZonedDateTime modifiedDate;
    protected String modifierUsername;
    protected String answerText;
    @OneToOne
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    protected AssessmentQuestion question;

    @Override
    public String toString() {
        return "PossibleAnswer{" +
                "id=" + id +
                ", answerText='" + answerText + '\'' +
                ", question=" + question.getQuestion() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PossibleAnswer that = (PossibleAnswer) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(createdDate, that.createdDate)) return false;
        if (!Objects.equals(creatorUsername, that.creatorUsername))
            return false;
        if (!Objects.equals(modifiedDate, that.modifiedDate)) return false;
        if (!Objects.equals(modifierUsername, that.modifierUsername))
            return false;
        if (!Objects.equals(answerText, that.answerText)) return false;
        return Objects.equals(question, that.question);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (creatorUsername != null ? creatorUsername.hashCode() : 0);
        result = 31 * result + (modifiedDate != null ? modifiedDate.hashCode() : 0);
        result = 31 * result + (modifierUsername != null ? modifierUsername.hashCode() : 0);
        result = 31 * result + (answerText != null ? answerText.hashCode() : 0);
        result = 31 * result + (question != null ? question.hashCode() : 0);
        return result;
    }
}
