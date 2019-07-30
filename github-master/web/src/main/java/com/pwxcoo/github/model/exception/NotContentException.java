package com.pwxcoo.github.model.exception;

/**
 * @author pwxcoo
 * @package com.pwxcoo.github.model.exception
 * @email pwxcoo@gmail.com
 * @time 2018/10/04 22:36
 * @description 204 Not Content – the request has succeeded and no need to return any content ( ex:- such as update operation)
 */
public class NotContentException extends Exception {

    public NotContentException(String message) {
        super(message);
    }

}
