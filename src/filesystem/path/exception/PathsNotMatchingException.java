package filesystem.path.exception;

/**
 * @author Patrick
 * @created 06.07.2016
 */
public class PathsNotMatchingException extends Exception{

    public PathsNotMatchingException() {
        super();
    }

    public PathsNotMatchingException(String message) {
        super(message);
    }

    public PathsNotMatchingException(String message, Throwable cause) {
        super(message, cause);
    }

}
