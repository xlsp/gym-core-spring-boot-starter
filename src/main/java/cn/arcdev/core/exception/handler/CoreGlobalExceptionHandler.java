package cn.arcdev.core.exception.handler;

import cn.arcdev.core.constant.ErrorCodes;
import cn.arcdev.core.dto.Response;
import cn.arcdev.core.exception.ApplicationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Core global exception handler that will catch all exceptions in the application.
 *
 * @author Kraken
 */
@RestControllerAdvice
public class CoreGlobalExceptionHandler {
    /**
     * Handle all exceptions.
     *
     * @param exception exception thrown
     * @return restful response
     */
    @ExceptionHandler(Throwable.class)
    public Response<Object> handle(Throwable exception) {
        Response<Object> response = new Response<>();
        if (exception instanceof ApplicationException) {
            response.setStatus(((ApplicationException) exception).getStatus());
            response.setMessage(exception.getMessage());
        } else {
            response.setStatus(ErrorCodes.DEFAULT_ERROR_CODE);
            response.setMessage("Unknown error");
        }
        return response;
    }
}
