package Exceptions;

public class VariableNotFoundException extends RuntimeException{
    public VariableNotFoundException(String message){
        super(message);
    }
}
