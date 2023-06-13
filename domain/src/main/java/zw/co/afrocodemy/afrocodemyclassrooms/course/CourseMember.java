package zw.co.afrocodemy.afrocodemyclassrooms.course;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CourseMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @ManyToOne
    @JoinColumn(name = "course_id")
    protected Course course;
    protected String takerUsername;
    @Enumerated(EnumType.STRING)
    protected CourseMemberProgress progress;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourseMember that = (CourseMember) o;

        if (!id.equals(that.id)) return false;
        if (!course.equals(that.course)) return false;
        if (!takerUsername.equals(that.takerUsername)) return false;
        return progress == that.progress;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + course.hashCode();
        result = 31 * result + takerUsername.hashCode();
        result = 31 * result + progress.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "CourseMember{" +
                "id=" + id +
                ", course=" + course +
                ", takerUsername='" + takerUsername + '\'' +
                ", progress=" + progress +
                '}';
    }
}
