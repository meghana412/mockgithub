package com.pwxcoo.github.api.restful;

import com.pwxcoo.github.dto.FollowRelationshipDto;
import com.pwxcoo.github.model.exception.ServerException;
import com.pwxcoo.github.model.exception.UnauthorizedException;
import com.pwxcoo.github.service.followRelationship.FollowRelationshipService;
import com.pwxcoo.github.utils.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author pwxcoo
 * @package com.pwxcoo.github.api.restful
 * @email pwxcoo@gmail.com
 * @time 2018/10/05 18:56
 * @description
 */
@RestController
@Slf4j
@RequestMapping(value = "/api")
public class FollowRelationshipRestController {

    @Autowired
    FollowRelationshipService followRelationshipService;


    @RequestMapping(value = "/following/{followerUsername}/{followingUsername}", method = RequestMethod.GET)
    public Boolean getFollowRelationship(@PathVariable String followerUsername, @PathVariable String followingUsername) throws UnauthorizedException {
//        return followRelationshipService.checkFollowRelationshipByUsername(followerUsername, followingUsername);
        if (SessionUtil.session().isPresent()) {

            return followRelationshipService.checkFollowRelationshipByUsername(SessionUtil.getUsername(), followingUsername);
        } else throw new UnauthorizedException("The user is not logined!");
    }

    @RequestMapping(value = "/following/", method = RequestMethod.POST)
    public Boolean createFollowRelationship(@RequestBody FollowRelationshipDto followRelationshipDto) throws UnauthorizedException, ServerException {
        if (followRelationshipDto.getFollowerId() == null) {
            if ( SessionUtil.session().isPresent() == false) {
                throw new UnauthorizedException("The user is not logined!");
            }
        }

        log.info(followRelationshipDto.toString());
        if (followRelationshipService.addFollowRelationship(SessionUtil.getUsername(),followRelationshipDto.getFollowingUsername())) {

            return followRelationshipService.checkFollowRelationshipByUsername(SessionUtil.getUsername(),followRelationshipDto.getFollowingUsername());
        }
        throw new ServerException("create follow relationship failed!");
    }

    @RequestMapping(value = "/following/{followerUsername}/{followingUsername}", method = RequestMethod.DELETE)
    public Boolean deleteFollowRelationship(@PathVariable String followerUsername, @PathVariable String followingUsername) throws UnauthorizedException, ServerException {
        return followRelationshipService.deleteFollowRelationship(followerUsername, followingUsername);
//        if (SessionUtil.session().isPresent()) {
//
//            return followRelationshipService.deleteFollowRelationship(SessionUtil.getUsername(), followingUsername);
//        } else throw new UnauthorizedException("The user is not logined!");
    }


    @RequestMapping(value = "/following/{followerUsername}/", method = RequestMethod.GET)
    public List<FollowRelationshipDto> getAllFollowingByCurrentUser(@PathVariable String followerUsername) throws UnauthorizedException, ServerException {
        return followRelationshipService.getAllFollowingByFollowerName(followerUsername);
//        if (SessionUtil.session().isPresent()) {
//            return followRelationshipService.getAllFollowingByFollowerId(SessionUtil.getUserId());
//        } else throw new UnauthorizedException("The user is not logined!");
    }

    @RequestMapping(value = "/follower/{followingUsername}/", method = RequestMethod.GET)
    public List<FollowRelationshipDto> getAllFollowerByCurrentUser(@PathVariable String followingUsername) throws UnauthorizedException, ServerException {
        return followRelationshipService.getAllFollowerByFollowingName(followingUsername);
//        if (SessionUtil.session().isPresent()) {
//            return followRelationshipService.getAllFollowerByFollowingId(SessionUtil.getUserId());
//        } else throw new UnauthorizedException("The user is not logined!");
    }
}
