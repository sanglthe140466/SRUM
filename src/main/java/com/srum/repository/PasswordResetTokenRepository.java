package com.srum.repository;

import com.srum.entity.PasswordResetToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ThoNN1
 */
@Repository
public interface PasswordResetTokenRepository extends CrudRepository<PasswordResetToken, Long> {
    PasswordResetToken findByToken(String token);
}
