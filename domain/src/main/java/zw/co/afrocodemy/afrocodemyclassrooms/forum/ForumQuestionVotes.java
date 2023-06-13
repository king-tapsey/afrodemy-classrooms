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
public class ForumQuestionVotes extends ForumVotes{
    @ManyToOne
    protected ForumQuestion forumQuestion;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ForumQuestionVotes that = (ForumQuestionVotes) o;

        return Objects.equals(forumQuestion, that.forumQuestion);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (forumQuestion != null ? forumQuestion.hashCode() : 0);
        return result;
    }
}
