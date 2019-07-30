package com.pwxcoo.github.service.repositorySubscription;

import com.pwxcoo.github.dto.RepositorySubscriptionDto;
import com.pwxcoo.github.model.type.Action;

import java.util.List;

/**
 * @author pwxcoo
 * @package com.pwxcoo.github.service
 * @email pwxcoo@gmail.com
 * @time 2018/10/05 15:46
 * @description
 */
public interface RepositorySubscriptionService {

    List<RepositorySubscriptionDto> getRepositorySubscription(Long userId);

    Boolean insertRepositorySubscription(Long userId, Action action, Long repositoryId);
}
