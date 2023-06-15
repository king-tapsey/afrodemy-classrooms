package zw.co.afrocodemy.afrocodemyclassrooms.forum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import zw.co.afrocodemy.afrocodemyclassrooms.BaseEntity;

import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ForumAnswer extends BaseEntity {
    protected ZonedDateTime modifiedDate;
    protected String text;
    @ManyToOne(cascade = CascadeType.REMOVE)
    protected ForumQuestion question;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ForumAnswer that = (ForumAnswer) o;

        if (!Objects.equals(modifiedDate, that.modifiedDate)) return false;
        if (!Objects.equals(text, that.text)) return false;
        return Objects.equals(question, that.question);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (modifiedDate != null ? modifiedDate.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (question != null ? question.hashCode() : 0);
        return result;
    }
}
