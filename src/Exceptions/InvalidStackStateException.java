package Exceptions;

public class InvalidStackStateException extends RuntimeException {
    public InvalidStackStateException(String message){
        super(message);
    }
}
