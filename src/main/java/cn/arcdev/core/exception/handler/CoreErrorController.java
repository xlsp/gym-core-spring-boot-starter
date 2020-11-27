package cn.arcdev.core.exception.handler;

import cn.arcdev.core.constant.ErrorCodes;
import cn.arcdev.core.exception.ApplicationException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * Core error controller.
 *
 * @author Kraken
 */
@Controller
@RequestMapping(CoreErrorController.ERROR_PATH)
public class CoreErrorController implements ErrorController {
    public static final String ERROR_PATH = "/error";

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    /**
     * Handle common error.
     *
     * @param request http request
     * @return pretty response
     */
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public void errorJson(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        throw new ApplicationException(ErrorCodes.DEFAULT_ERROR_CODE + status.value(), status.getReasonPhrase());
    }

    /**
     * Convert http request into http status.
     *
     * @param request http request
     * @return http status
     */
    protected HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        try {
            return HttpStatus.valueOf(statusCode);
        } catch (Exception ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
