package com.srum.service;

import com.srum.entity.PasswordResetToken;

/**
 * @author ThoNN1
 */
public interface ResetPasswordService {
    void saveToken(PasswordResetToken token);

    void sendEmail(PasswordResetToken token, String email, String contextPath);

    PasswordResetToken findByToken(String token);
}
