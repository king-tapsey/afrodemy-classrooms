package zw.co.afrocodemy.afrocodemyclassrooms.forum;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ForumQuestionUserVote extends ForumUserVote {
    @ManyToOne
    protected ForumQuestion question;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ForumQuestionUserVote that = (ForumQuestionUserVote) o;

        return Objects.equals(question, that.question);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (question != null ? question.hashCode() : 0);
        return result;
    }
}
