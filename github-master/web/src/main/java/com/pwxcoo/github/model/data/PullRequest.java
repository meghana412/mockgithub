package com.pwxcoo.github.model.data;

import com.pwxcoo.github.model.type.Status;
import lombok.Data;

/**
 * @author pwxcoo
 * @package com.pwxcoo.github.model
 * @email pwxcoo@gmail.com
 * @time 2018/10/03 17:07
 * @description
 */
@Data
public class PullRequest {

    private Long pullRequestId;
    private Long pullRequestNumber;
    private Long originRepositoryId;
    private Long nextRepositoryId;
    private Status status;
    private Long commentCount;
}
