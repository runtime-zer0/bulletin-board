package goormtask.bulletinboard.exception;

public class BoardNotFoundException extends RuntimeException{

    public BoardNotFoundException(String message) {
        super(message);
    }
}
