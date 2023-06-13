package zw.co.afrocodemy.afrocodemyclassrooms.exceptions;

public class CourseNotFoundException extends RuntimeException{
    public CourseNotFoundException(String message){
        super(message);
    }
    public CourseNotFoundException(Long id){
        super("Could not find course with id: " + id);
    }
}
