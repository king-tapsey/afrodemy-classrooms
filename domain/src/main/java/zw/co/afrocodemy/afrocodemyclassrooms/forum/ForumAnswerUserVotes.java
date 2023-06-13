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
public class ForumAnswerUserVotes extends ForumUserVotes{
    @ManyToOne
    protected ForumAnswer answer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ForumAnswerUserVotes that = (ForumAnswerUserVotes) o;

        return Objects.equals(answer, that.answer);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        return result;
    }
}
