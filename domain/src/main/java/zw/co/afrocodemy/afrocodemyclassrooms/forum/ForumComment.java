package zw.co.afrocodemy.afrocodemyclassrooms.forum;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.*;
import zw.co.afrocodemy.afrocodemyclassrooms.BaseEntity;

import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class ForumComment extends BaseEntity {
    protected String commentText;
    protected ZonedDateTime modifiedDate;
    @ElementCollection
    protected List<String> taggedUsers;
}
