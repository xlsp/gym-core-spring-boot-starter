package cn.arcdev.core.exception.handler;

import cn.arcdev.core.constant.ErrorCodes;
import cn.arcdev.core.dto.Response;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Customize {@link ResponseEntityExceptionHandler}.
 *
 * @author Kraken
 */
@RestControllerAdvice
public class CoreResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    protected Response<Object> buildResponseEntity(HttpStatus status) {
        return buildResponseEntity(status, status.getReasonPhrase());

    }

    protected Response<Object> buildResponseEntity(HttpStatus status, String message) {
        Response<Object> response = new Response<>();
        response.setStatus(ErrorCodes.DEFAULT_ERROR_CODE + status.value());
        response.setMessage(message);
        return response;
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers,
                                                                         HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(buildResponseEntity(status), HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers,
                                                                     HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(buildResponseEntity(status), HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers,
                                                                      HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(buildResponseEntity(status), HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
                                                               HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(buildResponseEntity(status), HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers,
                                                                          HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(buildResponseEntity(status), HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers,
                                                                          HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(buildResponseEntity(status), HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(buildResponseEntity(status), HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
                                                        HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(buildResponseEntity(status), HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(buildResponseEntity(status), HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(buildResponseEntity(status), HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        BindingResult bindingResult = ex.getBindingResult();
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
            return new ResponseEntity<>(buildResponseEntity(status, builder.toString()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(buildResponseEntity(status), HttpStatus.OK);
        }
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex, HttpHeaders headers,
                                                                     HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(buildResponseEntity(status), HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(buildResponseEntity(status), HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
                                                                   HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(buildResponseEntity(status), HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<Object> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException ex, HttpHeaders headers,
                                                                        HttpStatus status, WebRequest webRequest) {
        return new ResponseEntity<>(buildResponseEntity(status), HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(buildResponseEntity(status), HttpStatus.OK);
    }
}
