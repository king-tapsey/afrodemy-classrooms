package zw.co.afrocodemy.afrocodemyclassrooms.forum;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class ForumUserVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected ForumVoteType voteType;
    protected String voterUsername;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ForumUserVote that = (ForumUserVote) o;

        if (!id.equals(that.id)) return false;
        if (voteType != that.voteType) return false;
        return Objects.equals(voterUsername, that.voterUsername);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (voteType != null ? voteType.hashCode() : 0);
        result = 31 * result + (voterUsername != null ? voterUsername.hashCode() : 0);
        return result;
    }
}
