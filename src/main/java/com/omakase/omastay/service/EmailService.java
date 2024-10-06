package com.omakase.omastay.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import com.omakase.omastay.dto.custom.HostReservationEmailDTO;
import com.omakase.omastay.entity.Reservation;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
    private final RedisService redisService;
    private final ReservationService reservationService;

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
        System.out.println("인증번호:"+authCode);
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

    // 예약 정보를 포함한 이메일 발송
    public void sendReservationEmail(HostReservationEmailDTO reservation) throws MessagingException {
        String reservationKey = "reservation:" + reservation.getId();
        
        // 이미 메일을 보냈는지 확인
        if (redisService.existData(reservationKey)) {
            System.out.println("이미 메일을 보냈습니다: " + reservation.getId());
            return;
        }

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(reservation.getContactEmail());
        helper.setSubject("Omastay 예약 확인 요청 - 빠른 확인 부탁드립니다.");
        helper.setFrom(configEmail);

        ClassPathResource resource = new ClassPathResource("static/image/logo/logo.png");
        helper.addInline("logoImage", resource);

        Context context = new Context();
        context.setVariable("reservation", reservation);


        TemplateEngine templateEngine = new TemplateEngine();
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/host/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCacheable(false);
        templateResolver.setCharacterEncoding("UTF-8");
        templateEngine.setTemplateResolver(templateResolver);

        String htmlContent = templateEngine.process("reservation_mail", context);
        helper.setText(htmlContent, true);

        mailSender.send(message);

        // 메일 발송 후 상태 업데이트
        redisService.setDataExpire(reservationKey, "sent", 60 * 60 * 24); // 24시간 동안 유지
    }

    @Scheduled(fixedRate = 1800000) // 30분마다 실행
    public void sendPendingReservationEmails() {
        List<HostReservationEmailDTO> pendingReservations = reservationService.findReservationsByPending();
        for (HostReservationEmailDTO reservation : pendingReservations) {
            try {
                sendReservationEmail(reservation);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }

    // 예약 확정 이메일 발송
    public void sendResConfirmEmail(HostReservationEmailDTO reservation) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(reservation.getContactEmail());
        helper.setSubject("Omastay 예약 확정되었습니다.");
        helper.setFrom(configEmail);

        ClassPathResource resource = new ClassPathResource("static/image/logo/logo.png");
        helper.addInline("logoImage", resource);

        Context context = new Context();
        context.setVariable("reservation", reservation);


        TemplateEngine templateEngine = new TemplateEngine();
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/host/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCacheable(false);
        templateResolver.setCharacterEncoding("UTF-8");
        templateEngine.setTemplateResolver(templateResolver);

        String htmlContent = templateEngine.process("confirm_mail", context);
        helper.setText(htmlContent, true);

        mailSender.send(message);

    }

    // 예약 확정 이메일 발송
    public void sendResCancelEmail(HostReservationEmailDTO reservation) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(reservation.getContactEmail());
        helper.setSubject("Omastay 예약 취소되었습니다.");
        helper.setFrom(configEmail);
        
        ClassPathResource resource = new ClassPathResource("static/image/logo/logo.png");
        helper.addInline("logoImage", resource);

        Context context = new Context();
        context.setVariable("reservation", reservation);


        TemplateEngine templateEngine = new TemplateEngine();
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/host/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCacheable(false);
        templateResolver.setCharacterEncoding("UTF-8");
        templateEngine.setTemplateResolver(templateResolver);

        String htmlContent = templateEngine.process("cancel_mail", context);
        helper.setText(htmlContent, true);

        mailSender.send(message);

    }
}