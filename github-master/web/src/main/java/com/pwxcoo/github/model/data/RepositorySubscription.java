package com.pwxcoo.github.model.data;

import com.pwxcoo.github.model.type.Action;
import lombok.Data;

/**
 * @author pwxcoo
 * @package com.pwxcoo.github.model
 * @email pwxcoo@gmail.com
 * @time 2018/10/03 17:02
 * @description
 */
@Data
public class RepositorySubscription {

    private Long repositorySubscriptionId;
    private Long userId;
    private Action action;     // 'star', 'create', 'fork'
    private Long repositoryId;
}
