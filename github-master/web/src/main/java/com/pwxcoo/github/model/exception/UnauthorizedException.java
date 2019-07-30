package com.pwxcoo.github.model.exception;

/**
 * @author pwxcoo
 * @package com.pwxcoo.github.model.exception
 * @email pwxcoo@gmail.com
 * @time 2018/10/04 22:36
 * @description 401 Unauthorized – the request require user authorization.
 */
public class UnauthorizedException extends Exception {

    public UnauthorizedException(String message) {
        super(message);
    }

}
