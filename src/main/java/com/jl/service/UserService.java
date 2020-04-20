package com.jl.service;

import com.jl.vo.User;

/**
 * @author J-Lei
 * @date 2020/3/12 11:59
 */
public interface UserService {
    User checkUser(String username ,String password);
}
