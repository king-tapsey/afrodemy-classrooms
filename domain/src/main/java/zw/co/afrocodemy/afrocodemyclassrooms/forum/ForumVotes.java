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
public abstract class ForumVotes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected Integer upVotes;
    protected Integer downVotes;

    public Integer getNetVotes(){
        if(upVotes == null) upVotes = 0;
        if(downVotes == null) downVotes = 0;
        return upVotes - downVotes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ForumVotes that = (ForumVotes) o;

        if (!id.equals(that.id)) return false;
        if (!Objects.equals(upVotes, that.upVotes)) return false;
        return Objects.equals(downVotes, that.downVotes);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (upVotes != null ? upVotes.hashCode() : 0);
        result = 31 * result + (downVotes != null ? downVotes.hashCode() : 0);
        return result;
    }
}
