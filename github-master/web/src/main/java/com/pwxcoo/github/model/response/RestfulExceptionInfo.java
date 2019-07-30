package com.pwxcoo.github.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author pwxcoo
 * @package com.pwxcoo.github.model.exception
 * @email pwxcoo@gmail.com
 * @time 2018/10/04 16:36
 * @description
 */
@Data
@AllArgsConstructor
public class RestfulExceptionInfo {

    private Integer code;
    private String message;
    private String url;
}
