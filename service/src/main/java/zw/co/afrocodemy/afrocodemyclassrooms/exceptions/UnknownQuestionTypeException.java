package zw.co.afrocodemy.afrocodemyclassrooms.exceptions;

public class UnknownQuestionTypeException extends RuntimeException{
    public UnknownQuestionTypeException(String message){
        super(message);
    }
}
