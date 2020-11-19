package cn.arcdev.core.exception;

import cn.arcdev.core.constant.ErrorCodes;

/**
 * Validation exception.
 *
 * @author Kraken
 */
public class ValidationException extends ApplicationException {
    /**
     * Validation exception constructor.
     *
     * @param parameterName name of the invalid parameter
     */
    public ValidationException(String parameterName) {
        super(ErrorCodes.INVALID_PARAMETER, "\"" + parameterName + "\"参数格式错误");
    }
}
