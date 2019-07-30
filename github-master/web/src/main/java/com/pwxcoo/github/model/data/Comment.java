package com.pwxcoo.github.model.data;

import lombok.Data;

/**
 * @author pwxcoo
 * @package com.pwxcoo.github.model
 * @email pwxcoo@gmail.com
 * @time 2018/10/03 17:12
 * @description
 */
@Data
public class Comment {

    private Long commentId;
    private Long commentNumber;
    private Long issueId;
    private Long pullRequestId;

}
