package zw.co.afrocodemy.afrocodemyclassrooms.exceptions;

public class InvalidRequestException extends RuntimeException{
    public InvalidRequestException(String message){
        super(message);
    }
}
