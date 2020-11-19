package cn.arcdev.core.exception;

import cn.arcdev.core.constant.ErrorCodes;

/**
 * Application Exception.
 *
 * @author Kraken
 */
public class ApplicationException extends RuntimeException {
    private final int status;

    public ApplicationException() {
        super("Unknown error.");
        this.status = ErrorCodes.DEFAULT_ERROR_CODE;
    }

    public ApplicationException(int status, String message) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
