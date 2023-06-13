package zw.co.afrocodemy.afrocodemyclassrooms.topic;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.*;
import zw.co.afrocodemy.afrocodemyclassrooms.BaseEntity;
import zw.co.afrocodemy.afrocodemyclassrooms.course.Course;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Topic extends BaseEntity {
    protected ZonedDateTime modifiedDate;
    protected String modifierUsername;
    protected String title;
    protected String details;
    @ManyToOne(fetch = FetchType.EAGER)
    protected Topic precededBy;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Topic topic = (Topic) o;

        if (!Objects.equals(modifiedDate, topic.modifiedDate)) return false;
        if (!Objects.equals(modifierUsername, topic.modifierUsername)) return false;
        if (!Objects.equals(title, topic.title)) return false;
        if (!Objects.equals(details, topic.details)) return false;
        return Objects.equals(precededBy, topic.precededBy);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (modifiedDate != null ? modifiedDate.hashCode() : 0);
        result = 31 * result + (modifierUsername != null ? modifierUsername.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (details != null ? details.hashCode() : 0);
        result = 31 * result + (precededBy != null ? precededBy.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", createdDate=" + createdDate +
                ", creatorUsername='" + creatorUsername + '\'' +
                ", modifiedDate=" + modifiedDate +
                ", modifierUsername='" + modifierUsername + '\'' +
                ", title='" + title + '\'' +
                ", details='" + details + '\'' +
                ", precededBy=" + precededBy +
                '}';
    }
}
