package com.pwxcoo.github.service.user;

import com.pwxcoo.github.mapper.UserMapper;
import com.pwxcoo.github.model.data.User;
import com.pwxcoo.github.utils.AvatarUtil;
import com.pwxcoo.github.utils.PasswordUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author pwxcoo
 * @package com.pwxcoo.github.service.user
 * @email pwxcoo@gmail.com
 * @time 2018/10/01 12:47
 * @description
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public Boolean checkUserIsExist(String email, String username) {

        return false;
    }

    @Override
    public User getUserByUserId(Long userId) {
        return userMapper.findUserByUserId(userId);
    }

    @Override
    public User getUserByEmail(String email) {
        return userMapper.findUserByEmail(email);
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }

    @Override
    public Boolean createUser(User user) {
        try {
            user.setSalt(PasswordUtil.getNextSalt());
            user.setPassword(PasswordUtil.hash(user.getPassword().toCharArray(), user.getSalt()));
            user.setAvatar(AvatarUtil.generateAvatar());
            if (userMapper.insertUser(user.getUsername(), user.getAvatar(), user.getEmail(), user.getPassword(), user.getSalt()) > 0)
                return true;
        } catch (Exception e) {
            log.error("Error when insert User: " + user.toString());
            return false;
        }
        return false;
    }

    @Override
    public Boolean validateUser(String email, String password) {
        User realUser = userMapper.findUserByEmail(email);
        if (realUser == null) return false;
        log.info(realUser.toString());
        return PasswordUtil.isExpectedPassword(password.toCharArray(), realUser.getSalt(), realUser.getPassword().toCharArray());
    }

    @Override
    public Boolean deleteUserByEmail(String email) {
        try {
            userMapper.deleteUserByEmail(email);
            return true;
        } catch (Exception e) {
            log.error("Error when delete user: " + email);
            return false;
        }
    }

}
