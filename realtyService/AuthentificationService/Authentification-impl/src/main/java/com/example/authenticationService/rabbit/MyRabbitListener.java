package com.example.authenticationService.rabbit;


import com.example.authenticationService.model.jooq.schema.tables.pojos.CianUserEntity;
import com.example.authenticationService.model.jooq.schema.tables.pojos.VerificationTokenEntity;
import com.example.authenticationService.repository.CianUserRepository;
import com.example.authenticationService.repository.RefreshTokenRepository;
import com.example.authenticationService.repository.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MyRabbitListener {


    private final JavaMailSender javaMailSender;

    private final CianUserRepository cianUserRepository;

    private final VerificationTokenRepository tokenRepository;

    private final RefreshTokenRepository refreshTokenRepository;

    @RabbitListener(queues = "${service-rabbit.queueRegistration-name}", ackMode = "AUTO")
    public void getMessageToRegistration(String login) {
        CianUserEntity user = cianUserRepository.findByLogin(login);
        UUID token = UUID.randomUUID();
        VerificationTokenEntity entityToken = new VerificationTokenEntity(token.toString(), user.getId(), LocalDateTime.now(), token);
        tokenRepository.save(entityToken);


        String subject = "Registration confirmed";
        String confirmationUrl = "http://localhost:8080/user/approveCianUser?token=" + token.toString();

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(login);
        email.setSubject(subject);
        email.setText(confirmationUrl);
        javaMailSender.send(email);
    }

    @RabbitListener(queues = "${service-rabbit.queueResetPassword-name}", ackMode = "AUTO")
    public void getMessageToResetPassword(String login) {
        CianUserEntity user = cianUserRepository.findByLogin(login);
        UUID token = UUID.randomUUID();
        VerificationTokenEntity entityToken = new VerificationTokenEntity(token.toString(), user.getId(), LocalDateTime.now(), token);
        tokenRepository.save(entityToken);

        String subject = "Reset password";
        String confirmationUrl = "http://localhost:8080/user/resetPassword?token=" + token.toString();

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(login);
        email.setSubject(subject);
        email.setText(confirmationUrl);
        javaMailSender.send(email);

    }

    @RabbitListener(queues = "${service-rabbit.queueResetLogin-name}", ackMode = "AUTO")
    public void getMessageToResetLogin(String login) {
        CianUserEntity user = cianUserRepository.findByLogin(login);
        UUID token = UUID.randomUUID();
        VerificationTokenEntity entityToken = new VerificationTokenEntity(token.toString(), user.getId(), LocalDateTime.now(), token);
        tokenRepository.save(entityToken);

        String subject = "Reset login";
        String confirmationUrl = "http://localhost:8080/user/resetLogin?token=" + token.toString();

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(login);
        email.setSubject(subject);
        email.setText(confirmationUrl);
        javaMailSender.send(email);
        refreshTokenRepository.deleteByUserId(user.getId());
    }
}
