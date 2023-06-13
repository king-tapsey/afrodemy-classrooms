package zw.co.afrocodemy.afrocodemyclassrooms.forum;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.*;
import zw.co.afrocodemy.afrocodemyclassrooms.BaseEntity;

import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Forum extends BaseEntity {
    protected ZonedDateTime modifiedDate;
    protected String modifierUsername;
    protected String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Forum forum = (Forum) o;

        if (!modifiedDate.equals(forum.modifiedDate)) return false;
        if (!modifierUsername.equals(forum.modifierUsername)) return false;
        return name.equals(forum.name);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + modifiedDate.hashCode();
        result = 31 * result + modifierUsername.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }
}
