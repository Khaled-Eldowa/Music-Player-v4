package exceptions;

public class EmptyFieldException extends Exception {
    public EmptyFieldException(String fieldName) {
        super("The Field " + fieldName + " cannot be left empty!");
    }
}
