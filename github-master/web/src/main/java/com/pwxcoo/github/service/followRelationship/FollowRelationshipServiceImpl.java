package com.pwxcoo.github.service.followRelationship;

import com.pwxcoo.github.dto.FollowRelationshipDto;
import com.pwxcoo.github.mapper.FollowRelationshipMapper;
import com.pwxcoo.github.mapper.UserMapper;
import com.pwxcoo.github.mapper.UserSubscriptionMapper;
import com.pwxcoo.github.model.data.FollowRelationship;
import com.pwxcoo.github.model.type.Action;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author pwxcoo
 * @package com.pwxcoo.github.service.followRelationship
 * @email pwxcoo@gmail.com
 * @time 2018/10/05 18:51
 * @description
 */
@Slf4j
@Service
public class FollowRelationshipServiceImpl implements FollowRelationshipService{

    @Autowired
    FollowRelationshipMapper followRelationshipMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserSubscriptionMapper userSubscriptionMapper;

    @Override
    public Boolean checkFollowRelationshipByUserId(Long followerId, Long followingId) {
        if (followRelationshipMapper.getFollowRelationship(followerId, followingId) != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean checkFollowRelationshipByUserId(FollowRelationship followRelationship) {
        return checkFollowRelationshipByUserId(followRelationship.getFollowerId(), followRelationship.getFollowingId());
    }

    @Override
    public Boolean checkFollowRelationshipByUsername(String followerUsername, String followingUsername) {
        return checkFollowRelationshipByUserId(userMapper.findUserByUsername(followerUsername).getUserId(),
                userMapper.findUserByUsername(followingUsername).getUserId());
    }

    @Override
    @Transactional
    public Boolean addFollowRelationship(Long followerId, Long followingId) {
        if (followRelationshipMapper.addFollowRelationship(followerId, followingId) > 0) {
            userSubscriptionMapper.insertUserSubscription(followerId, Action.FOLLOW, followingId);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean addFollowRelationship(String followerUsername, String followingUsername) {
        return addFollowRelationship(userMapper.findUserByUsername(followerUsername).getUserId(),
                userMapper.findUserByUsername(followingUsername).getUserId());
    }

    @Override
    public Boolean addFollowRelationship(FollowRelationship followRelationship) {
        return addFollowRelationship(followRelationship.getFollowerId(), followRelationship.getFollowingId());
    }

    @Override
    public Boolean deleteFollowRelationship(Long followerId, Long followingId) {
        if (followRelationshipMapper.deleteFollowRelationship(followerId, followingId) > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean deleteFollowRelationship(String followerUsername, String followingUsername) {
        return deleteFollowRelationship(userMapper.findUserByUsername(followerUsername).getUserId(),
                userMapper.findUserByUsername(followingUsername).getUserId());
    }

    @Override
    public List<FollowRelationshipDto> getAllFollowingByFollowerId(Long followerId) {
        return followRelationshipMapper.getAllFollowingByFollowerId(followerId);
    }

    @Override
    public List<FollowRelationshipDto> getAllFollowingByFollowerName(String followerName) {
        return followRelationshipMapper.getAllFollowingByFollowerId(userMapper.findUserByUsername(followerName).getUserId());
    }

    @Override
    public List<FollowRelationshipDto> getAllFollowerByFollowingId(Long followingId) {
        return followRelationshipMapper.getAllFollowerByFollowingId(followingId);
    }

    @Override
    public List<FollowRelationshipDto> getAllFollowerByFollowingName(String followingUsername) {
        return followRelationshipMapper.getAllFollowerByFollowingId(userMapper.findUserByUsername(followingUsername).getUserId());
    }
}
