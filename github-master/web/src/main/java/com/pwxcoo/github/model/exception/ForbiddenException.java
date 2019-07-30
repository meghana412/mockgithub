package com.pwxcoo.github.model.exception;

/**
 * @author pwxcoo
 * @package com.pwxcoo.github.model.exception
 * @email pwxcoo@gmail.com
 * @time 2018/10/04 22:36
 * @description 403 Forbidden – the server refusing to fulfill the request.
 */
public class ForbiddenException extends Exception {

    public ForbiddenException(String message) {
        super(message);
    }

}
