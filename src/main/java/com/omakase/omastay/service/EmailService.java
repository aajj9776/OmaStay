package com.omakase.omastay.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
    private final RedisService redisService;

    @Value("${spring.mail.username}")
    private String configEmail;

    private String createdCode() {
        int leftLimit = 48; // number '0'
        int rightLimit = 122; // alphabet 'z'
        int targetStringLength = 6;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    private String setContext(String code) {
        Context context = new Context();
        TemplateEngine templateEngine = new TemplateEngine();
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();

        context.setVariable("code", code);

        templateResolver.setPrefix("templates/host/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCacheable(false);
        templateResolver.setCharacterEncoding("UTF-8");

        templateEngine.setTemplateResolver(templateResolver);

        return templateEngine.process("host_mail", context);
    }

    // 메일 반환
    private MimeMessage createEmailForm(String email) throws MessagingException {
        String authCode = createdCode();

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(email);
        helper.setSubject("Omastay 인증코드 안내드립니다.");
        helper.setFrom(configEmail);
        helper.setText(setContext(authCode), true);

        ClassPathResource resource = new ClassPathResource("static/image/logo/logo.png");
        helper.addInline("logoImage", resource);

        redisService.setDataExpire(email, authCode, 60 * 30L);

        return message;
    }

    // 메일 보내기
    public void sendEmail(String email) throws MessagingException {
            MimeMessage emailForm = createEmailForm(email);
            mailSender.send(emailForm);
    }

    // 코드 검증
    public Boolean verifyEmailCode(String email, String code) {
        String codeFoundByEmail = redisService.getData(email);
        System.out.println(codeFoundByEmail);
        if (codeFoundByEmail == null) {
            return false;
        }
        return codeFoundByEmail.equals(code);
    }
}