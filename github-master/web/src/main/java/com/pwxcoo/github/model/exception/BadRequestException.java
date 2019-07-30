package com.pwxcoo.github.model.exception;

/**
 * @author pwxcoo
 * @package com.pwxcoo.github.model.exception
 * @email pwxcoo@gmail.com
 * @time 2018/10/04 22:36
 * @description 400 Bad Request – the request could not be understood.
 */
public class BadRequestException extends Exception {

    public BadRequestException(String message) {
        super(message);
    }

}
