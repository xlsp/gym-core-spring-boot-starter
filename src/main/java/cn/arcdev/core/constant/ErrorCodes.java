package cn.arcdev.core.constant;

/**
 * Core error codes.
 *
 * @author Kraken
 */
public class ErrorCodes {
    private ErrorCodes() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    public static final int DEFAULT_ERROR_CODE = 10000000;
    public static final String DEFAULT_ERROR_MESSAGE = "Unknown error";
    public static final int INVALID_PARAMETER = 10000001;
}
