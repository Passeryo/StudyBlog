package com.jl.dao;

import com.jl.vo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author J-Lei
 * @date 2020/3/12 12:00
 */
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsernameAndPassword(String username,String password);
}
