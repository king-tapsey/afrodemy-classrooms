package zw.co.afrocodemy.afrocodemyclassrooms.forum;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;
import zw.co.afrocodemy.afrocodemyclassrooms.course.Course;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class CourseForum extends Forum{
    @OneToOne
    protected Course course;
}
