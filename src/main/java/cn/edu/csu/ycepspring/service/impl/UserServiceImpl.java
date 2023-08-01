package cn.edu.csu.ycepspring.service.impl;


import cn.edu.csu.ycepspring.common.constants.RoleConstants;
import cn.edu.csu.ycepspring.common.exception.ServiceException;
import cn.edu.csu.ycepspring.common.utils.EncryptionUtils;
import cn.edu.csu.ycepspring.entity.AuthLocal;
import cn.edu.csu.ycepspring.entity.User;
import cn.edu.csu.ycepspring.entity.dto.LoginResp;
import cn.edu.csu.ycepspring.mapper.UserMapper;
import cn.edu.csu.ycepspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public LoginResp checkAccount(String username, String password) {
        AuthLocal auth = userMapper.selectPasswordByUsername(username);
        if (auth == null) {
            throw new ServiceException("用户名不存在");
        } else if (!EncryptionUtils.matches(password, auth.getPassword())) {
            throw new ServiceException("密码错误");
        }
        int roleId = userMapper.selectRoleIdByAccount(auth.getAccount());
        return new LoginResp(roleId, auth.getAccount(), username);
    }

    @Override
    @Transactional
    public LoginResp register(String username, String password) {
        List<String> usernameList = userMapper.selectAllUsername();
        if (usernameList.contains(username)) {
            throw new ServiceException("用户名已存在!");
        }
        User user = new User();
        user.setRoleId(RoleConstants.USER);
        userMapper.createUser(user);

        AuthLocal auth = new AuthLocal(user.getAccount(), username, password);
        userMapper.insertAuthLocal(auth);
        return new LoginResp(RoleConstants.USER, auth.getAccount(), auth.getUsername());
    }
}
