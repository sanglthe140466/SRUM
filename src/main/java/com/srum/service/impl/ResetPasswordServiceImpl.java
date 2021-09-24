package com.srum.service.impl;

import com.srum.entity.PasswordResetToken;
import com.srum.repository.PasswordResetTokenRepository;
import com.srum.service.ResetPasswordService;
import com.srum.util.Messages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ThoNN1
 */
@Service
public class ResetPasswordServiceImpl implements ResetPasswordService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResetPasswordServiceImpl.class);

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Override
    @Transactional
    public void saveToken(PasswordResetToken token) {
        passwordResetTokenRepository.save(token);
    }

    @Override
    public void sendEmail(PasswordResetToken token, String userEmail, String contextPath) {
        Context context = new Context();
        Map<String, Object> model = new HashMap<>();
        model.put("user", token.getUser());
        model.put("resetUrl", contextPath + "/password/reset/" + token.getToken());
        context.setVariables(model);
        String html = templateEngine.process("email/reset-password", context);

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            helper.setTo(userEmail);
            helper.setSubject(Messages.RESET_PASSWORD_MAIL_SUBJECT);
            helper.setText(html, true);

            mailSender.send(mimeMessage);
        } catch (Exception e) {
            LOGGER.error(e.toString());
        }
    }

    @Override
    public PasswordResetToken findByToken(String token) {
        return passwordResetTokenRepository.findByToken(token);
    }
}
