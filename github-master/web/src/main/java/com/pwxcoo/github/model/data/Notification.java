package com.pwxcoo.github.model.data;

import com.pwxcoo.github.model.type.Action;
import lombok.Data;

/**
 * @author pwxcoo
 * @package com.pwxcoo.github.model
 * @email pwxcoo@gmail.com
 * @time 2018/10/03 17:05
 * @description
 */
@Data
public class Notification {

    private Long notificationId;
    private Long userId;
    private Long repositoryId;
    private Action action;  // 'pull request', 'issue'

}
