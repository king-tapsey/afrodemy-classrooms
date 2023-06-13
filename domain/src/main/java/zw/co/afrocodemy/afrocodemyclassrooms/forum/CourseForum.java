package zw.co.afrocodemy.afrocodemyclassrooms.forum;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;
import zw.co.afrocodemy.afrocodemyclassrooms.course.Course;

import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CourseForum extends Forum{
    @OneToOne
    protected Course course;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CourseForum that = (CourseForum) o;

        return Objects.equals(course, that.course);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (course != null ? course.hashCode() : 0);
        return result;
    }
}
