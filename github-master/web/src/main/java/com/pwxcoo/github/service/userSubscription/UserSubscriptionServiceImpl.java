package com.pwxcoo.github.service.userSubscription;

import com.pwxcoo.github.dto.UserSubscriptionDto;
import com.pwxcoo.github.mapper.UserSubscriptionMapper;
import com.pwxcoo.github.model.type.Action;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author pwxcoo
 * @package com.pwxcoo.github.service.userSubscription
 * @email pwxcoo@gmail.com
 * @time 2018/10/05 12:18
 * @description
 */
@Service
@Slf4j
public class UserSubscriptionServiceImpl implements UserSubscriptionService {

    @Autowired
    UserSubscriptionMapper userSubscriptionMapper;

    @Override
    public List<UserSubscriptionDto> getUserSubscriptionByUserId(Long userId) {
        return userSubscriptionMapper.getSubscriptionByUserId(userId);
    }

    @Override
    public Boolean insertUserSubscription(Long userId, Action action, Long actionId) {
        if (userSubscriptionMapper.insertUserSubscription(userId, action, actionId) > 0) {
            return true;
        }
        return false;
    }
}
