package zw.co.afrocodemy.afrocodemyclassrooms.assessment;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import zw.co.afrocodemy.afrocodemyclassrooms.course.Course;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Exam extends Assessment{
    @ManyToOne
    @JoinColumn(name = "course_id")
    protected Course course;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Exam exam = (Exam) o;

        return course.equals(exam.course);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + course.hashCode();
        return result;
    }
}
