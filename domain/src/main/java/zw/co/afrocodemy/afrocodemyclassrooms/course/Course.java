package zw.co.afrocodemy.afrocodemyclassrooms.course;

import jakarta.persistence.*;
import lombok.*;
import zw.co.afrocodemy.afrocodemyclassrooms.BaseEntity;
import zw.co.afrocodemy.afrocodemyclassrooms.topic.Topic;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course extends BaseEntity {
    protected ZonedDateTime modifiedDate;
    protected String modifierUsername;
    protected String title;
    protected String subTitle;
    protected String details;
    @Enumerated(EnumType.STRING)
    protected CourseType courseType;
    @ManyToMany(fetch = FetchType.EAGER)
    protected Set<Topic> topics;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Course course = (Course) o;

        if (!Objects.equals(modifiedDate, course.modifiedDate))
            return false;
        if (!Objects.equals(modifierUsername, course.modifierUsername))
            return false;
        if (!Objects.equals(title, course.title)) return false;
        if (!Objects.equals(subTitle, course.subTitle)) return false;
        if (!Objects.equals(details, course.details)) return false;
        return Objects.equals(courseType, course.courseType);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (modifiedDate != null ? modifiedDate.hashCode() : 0);
        result = 31 * result + (modifierUsername != null ? modifierUsername.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (subTitle != null ? subTitle.hashCode() : 0);
        result = 31 * result + (details != null ? details.hashCode() : 0);
        result = 31 * result + (courseType != null ? courseType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Course{" +
                "modifiedDate=" + modifiedDate +
                ", modifierUsername='" + modifierUsername + '\'' +
                ", title='" + title + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", details='" + details + '\'' +
                ", courseType=" + courseType +
                ", id=" + id +
                ", createdDate=" + createdDate +
                ", creatorUsername='" + creatorUsername + '\'' +
                '}';
    }
}
