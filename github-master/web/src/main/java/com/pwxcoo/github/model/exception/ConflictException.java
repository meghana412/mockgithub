package com.pwxcoo.github.model.exception;

/**
 * @author pwxcoo
 * @package com.pwxcoo.github.model.exception
 * @email pwxcoo@gmail.com
 * @time 2018/10/04 22:36
 * @description 409 Conflict – the request can not be completed due to a resource conflict.
 */
public class ConflictException extends Exception {

    public ConflictException(String message) {
        super(message);
    }

}
