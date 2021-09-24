package com.srum.service;

import com.srum.entity.User;

/**
 * @author ThoNN1
 */
public interface UserService {
    User getUserByAccount(String account);

    void update(User user);
}
