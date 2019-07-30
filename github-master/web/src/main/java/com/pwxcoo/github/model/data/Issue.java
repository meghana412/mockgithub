package com.pwxcoo.github.model.data;

import com.pwxcoo.github.model.type.Status;
import lombok.Data;

/**
 * @author pwxcoo
 * @package com.pwxcoo.github.model
 * @email pwxcoo@gmail.com
 * @time 2018/10/03 17:10
 * @description
 */
@Data
public class Issue {

    private Long issueId;
    private Long issueNumber;
    private Long repositoryId;
    private Long userId;
    private String text;
    private Status status;     // 'open', 'closed'
    private Long commentCount;
}
