package zw.co.afrocodemy.afrocodemyclassrooms.course.dto;

import lombok.Builder;
import lombok.Data;
import zw.co.afrocodemy.afrocodemyclassrooms.course.CourseType;

@Data
@Builder
public class CourseCreateRequest {
    private String creatorUsername;
    private String title;
    private String subTitle;
    private String details;
    private CourseType courseType;
}
