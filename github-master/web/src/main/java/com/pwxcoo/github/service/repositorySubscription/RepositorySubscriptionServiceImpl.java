package com.pwxcoo.github.service.repositorySubscription;

import com.pwxcoo.github.dto.RepositorySubscriptionDto;
import com.pwxcoo.github.mapper.RepositorySubscriptionMapper;
import com.pwxcoo.github.model.type.Action;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author pwxcoo
 * @package com.pwxcoo.github.service.repositorySubscription
 * @email pwxcoo@gmail.com
 * @time 2018/10/05 15:47
 * @description
 */
@Slf4j
@Service
public class RepositorySubscriptionServiceImpl implements RepositorySubscriptionService{

    @Autowired
    RepositorySubscriptionMapper repositorySubscriptionMapper;

    @Override
    public List<RepositorySubscriptionDto> getRepositorySubscription(Long userId) {
        return repositorySubscriptionMapper.getRepositorySubscriptionByUserId(userId);
    }

    @Override
    public Boolean insertRepositorySubscription(Long userId, Action action, Long repositoryId) {
        if (repositorySubscriptionMapper.insertRepositorySubscription(userId, action, repositoryId) > 0) {
            return true;
        }
        return false;
    }
}
