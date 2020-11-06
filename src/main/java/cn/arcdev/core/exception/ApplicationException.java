package cn.arcdev.core.exception;

/**
 * Application Exception.
 *
 * @author Kraken
 */
public class ApplicationException extends RuntimeException {
    private int status;
    private String message;

    public ApplicationException() {
        super("Unknown error.");
        this.status = 10000000;
    }

    public ApplicationException(int status, String message) {
        super(message);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
