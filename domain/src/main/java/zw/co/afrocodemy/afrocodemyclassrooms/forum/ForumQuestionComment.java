package zw.co.afrocodemy.afrocodemyclassrooms.forum;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class ForumQuestionComment extends ForumComment {
    @ManyToOne
    protected ForumQuestion question;
}
