package cn.arcdev.core.exception.handler;

import cn.arcdev.core.constant.ErrorCodes;
import cn.arcdev.core.dto.Response;
import cn.arcdev.core.exception.ApplicationException;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

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
        if (exception instanceof ApplicationException) {
            return new Response<>().setStatus(((ApplicationException) exception).getStatus()).setMessage(exception.getMessage());
        } else if (exception instanceof HttpRequestMethodNotSupportedException) {
            return convertHttpStatus(HttpStatus.METHOD_NOT_ALLOWED);
        } else if (exception instanceof HttpMediaTypeNotSupportedException) {
            return convertHttpStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        } else if (exception instanceof HttpMediaTypeNotAcceptableException) {
            return convertHttpStatus(HttpStatus.NOT_ACCEPTABLE);
        } else if (exception instanceof MissingPathVariableException) {
            return convertHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        } else if (exception instanceof MissingServletRequestParameterException) {
            return convertHttpStatus(HttpStatus.BAD_REQUEST);
        } else if (exception instanceof ServletRequestBindingException) {
            return convertHttpStatus(HttpStatus.BAD_REQUEST);
        } else if (exception instanceof ConversionNotSupportedException) {
            return convertHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        } else if (exception instanceof TypeMismatchException) {
            return convertHttpStatus(HttpStatus.BAD_REQUEST);
        } else if (exception instanceof HttpMessageNotReadableException) {
            return convertHttpStatus(HttpStatus.BAD_REQUEST);
        } else if (exception instanceof HttpMessageNotWritableException) {
            return convertHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        } else if (exception instanceof MethodArgumentNotValidException) {
            return convertMethodArgumentNotValidException((MethodArgumentNotValidException) exception);
        } else if (exception instanceof MissingServletRequestPartException) {
            return convertHttpStatus(HttpStatus.BAD_REQUEST);
        } else if (exception instanceof BindException) {
            return convertHttpStatus(HttpStatus.BAD_REQUEST);
        } else if (exception instanceof NoHandlerFoundException) {
            return convertHttpStatus(HttpStatus.NOT_FOUND);
        } else if (exception instanceof AsyncRequestTimeoutException) {
            return convertHttpStatus(HttpStatus.SERVICE_UNAVAILABLE);
        }
        return new Response<>().setStatus(ErrorCodes.DEFAULT_ERROR_CODE).setMessage(ErrorCodes.DEFAULT_ERROR_MESSAGE);
    }

    /**
     * Convert http status into response.
     *
     * @param status http status
     * @return pretty response
     */
    protected Response<Object> convertHttpStatus(HttpStatus status) {
        return new Response<>().setStatus(ErrorCodes.DEFAULT_ERROR_CODE + status.value()).setMessage(status.getReasonPhrase());
    }

    /**
     * Convert {@link MethodArgumentNotValidException} into response.
     *
     * @param exception method argument validation exception
     * @return pretty response
     */
    protected Response<Object> convertMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        if (bindingResult.hasErrors()) {
            StringBuilder builder = new StringBuilder();
            for (ObjectError error : bindingResult.getAllErrors()) {
                Object[] arguments = error.getArguments();
                if (arguments == null || arguments.length == 0) {
                    continue;
                }
                for (Object argument : arguments) {
                    if (argument instanceof DefaultMessageSourceResolvable) {
                        builder.append(((DefaultMessageSourceResolvable) argument).getDefaultMessage()).append(",");
                    }
                }
                builder.append(error.getDefaultMessage()).append(";");
            }
            return new Response<>().setStatus(ErrorCodes.DEFAULT_ERROR_CODE + HttpStatus.BAD_REQUEST.value())
                    .setMessage(builder.toString());
        } else {
            return new Response<>().setStatus(ErrorCodes.DEFAULT_ERROR_CODE).setMessage(ErrorCodes.DEFAULT_ERROR_MESSAGE);
        }
    }
}
