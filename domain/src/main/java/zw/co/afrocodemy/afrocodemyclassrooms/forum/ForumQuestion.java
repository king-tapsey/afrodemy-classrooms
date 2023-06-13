package zw.co.afrocodemy.afrocodemyclassrooms.forum;

import jakarta.persistence.Entity;
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
@EqualsAndHashCode
public class ForumQuestion extends BaseEntity {
    protected ZonedDateTime modifiedDate;
    protected String text;
}
