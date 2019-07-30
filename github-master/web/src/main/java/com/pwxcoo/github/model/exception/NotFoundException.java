package com.pwxcoo.github.model.exception;

/**
 * @author pwxcoo
 * @package com.pwxcoo.github.exception
 * @email pwxcoo@gmail.com
 * @time 2018/10/04 21:55
 * @description 404 Not found – the server can not find anything that related to the request ( ex:- try to get user by id that not in the server).
 */
public class NotFoundException extends Exception {

    public NotFoundException(String message) {
        super(message);
    }

}
