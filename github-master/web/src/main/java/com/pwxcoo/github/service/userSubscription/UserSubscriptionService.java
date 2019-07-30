package com.pwxcoo.github.service.userSubscription;

import com.pwxcoo.github.dto.UserSubscriptionDto;
import com.pwxcoo.github.model.type.Action;

import java.util.List;

/**
 * @author pwxcoo
 * @package com.pwxcoo.github.service.userSubscription
 * @email pwxcoo@gmail.com
 * @time 2018/10/05 12:18
 * @description
 */
public interface UserSubscriptionService {

    List<UserSubscriptionDto> getUserSubscriptionByUserId(Long userId);

    Boolean insertUserSubscription(Long userId, Action action, Long actionId);
}
