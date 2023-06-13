package zw.co.afrocodemy.afrocodemyclassrooms.course.dto;

import lombok.Builder;
import lombok.Data;
import zw.co.afrocodemy.afrocodemyclassrooms.course.CourseType;
import zw.co.afrocodemy.afrocodemyclassrooms.topic.Topic;

import java.util.Set;

@Data
@Builder
public class CourseUpdateRequest {
    private Long id;
    private String modifierUsername;
    private String title;
    private String subTitle;
    private String details;
    private CourseType courseType;
}
