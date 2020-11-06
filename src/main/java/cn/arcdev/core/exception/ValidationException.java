package cn.arcdev.core.exception;

import cn.arcdev.core.constant.ErrorCodes;

/**
 * Validation exception.
 *
 * @author Kraken
 */
public class ValidationException extends ApplicationException {
    public ValidationException(String parameterName) {
        super(ErrorCodes.INVALID_PARAMETER, String.format("\"s%\"参数格式错误", parameterName));
    }
}
