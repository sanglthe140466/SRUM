package com.srum.repository;

import com.srum.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ThoNN1
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User getByAccount(String account);
}
