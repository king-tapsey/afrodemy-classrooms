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
public class ForumAnswerVotes extends ForumVotes{
    @ManyToOne
    protected ForumAnswer forumAnswer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ForumAnswerVotes that = (ForumAnswerVotes) o;

        return Objects.equals(forumAnswer, that.forumAnswer);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (forumAnswer != null ? forumAnswer.hashCode() : 0);
        return result;
    }
}
