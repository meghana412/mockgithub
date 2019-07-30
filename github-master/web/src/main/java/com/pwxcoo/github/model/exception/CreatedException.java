package com.pwxcoo.github.model.exception;

/**
 * @author pwxcoo
 * @package com.pwxcoo.github.model.exception
 * @email pwxcoo@gmail.com
 * @time 2018/10/04 22:36
 * @description 201 Created – the request has succeeded and a request was created a new resource ( ex:- create a new user).
 */
public class CreatedException extends Exception {

    public CreatedException(String message) {
        super(message);
    }

}
