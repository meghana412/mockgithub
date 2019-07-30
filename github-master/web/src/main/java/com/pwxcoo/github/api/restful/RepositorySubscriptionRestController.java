package com.pwxcoo.github.api.restful;

import com.pwxcoo.github.dto.RepositorySubscriptionDto;
import com.pwxcoo.github.model.exception.NotFoundException;
import com.pwxcoo.github.model.exception.UnauthorizedException;
import com.pwxcoo.github.service.repositorySubscription.RepositorySubscriptionService;
import com.pwxcoo.github.service.user.UserService;
import com.pwxcoo.github.utils.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author pwxcoo
 * @package com.pwxcoo.github.api.restful
 * @email pwxcoo@gmail.com
 * @time 2018/10/05 15:33
 * @description
 */
@RestController
@Slf4j
@RequestMapping(value = "/api/repositorySubscription")
public class RepositorySubscriptionRestController {

    @Autowired
    RepositorySubscriptionService repositorySubscriptionService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<RepositorySubscriptionDto> getSubscriptionByCurrentUserId() throws NotFoundException, UnauthorizedException {
        if (SessionUtil.session().isPresent() == false) {
            throw new UnauthorizedException("The user is not logined!");
        } else if (userService.getUserByUserId(SessionUtil.getUserId()) == null) {
            throw new NotFoundException("There is no this user!");
        }

        return repositorySubscriptionService.getRepositorySubscription(SessionUtil.getUserId());
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public List<RepositorySubscriptionDto> getSubscriptionByUserId(@PathVariable("userId") Long userId) throws NotFoundException {
        if (userService.getUserByUserId(userId) == null) {
            throw new NotFoundException("There is no this user!");
        }

        return repositorySubscriptionService.getRepositorySubscription(userId);
    }


}
