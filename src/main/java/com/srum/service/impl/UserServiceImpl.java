package com.srum.service.impl;

import com.srum.entity.User;
import com.srum.repository.UserRepository;
import com.srum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ThoNN1
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserByAccount(String account) {
        return userRepository.getByAccount(account);
    }

    @Override
    @Transactional
    public void update(User user) {
        userRepository.save(user);
    }
}
