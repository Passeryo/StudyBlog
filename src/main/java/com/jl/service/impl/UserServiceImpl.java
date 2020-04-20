package com.jl.service.impl;

import com.jl.dao.UserRepository;
import com.jl.service.UserService;
import com.jl.util.MD5Utils;
import com.jl.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author J-Lei
 * @date 2020/3/12 12:02
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username,MD5Utils.code(password));
        return user;
    }
}
