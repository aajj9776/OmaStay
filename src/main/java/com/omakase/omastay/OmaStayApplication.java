package com.omakase.omastay;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Objects;
import java.util.logging.Logger;

@SpringBootApplication
@EnableScheduling
public class OmaStayApplication {
    
    private static final Logger logger = Logger.getLogger(OmaStayApplication.class.getName());

    public static void main(String[] args) {

        Dotenv dotenv = Dotenv.configure().directory("/home/ubuntu/OmaStay").load();

        // 로그 추가
        logger.info("Loading environment variables from .env file");

        System.setProperty("DB_URL", Objects.requireNonNull(dotenv.get("DB_URL")));
        System.setProperty("DB_USERNAME", Objects.requireNonNull(dotenv.get("DB_USERNAME")));
        System.setProperty("DB_PASSWORD", Objects.requireNonNull(dotenv.get("DB_PASSWORD")));
        System.setProperty("MAIL_USERNAME", Objects.requireNonNull(dotenv.get("MAIL_USERNAME")));
        System.setProperty("MAIL_PASSWORD", Objects.requireNonNull(dotenv.get("MAIL_PASSWORD")));
        System.setProperty("JWT_SECRET_KEY", Objects.requireNonNull(dotenv.get("JWT_SECRET_KEY")));
        System.setProperty("REDIS_HOST", Objects.requireNonNull(dotenv.get("REDIS_HOST")));
        System.setProperty("REDIS_PORT", Objects.requireNonNull(dotenv.get("REDIS_PORT")));
        System.setProperty("GOOGLE_CLIENT_ID", Objects.requireNonNull(dotenv.get("GOOGLE_CLIENT_ID")));
        System.setProperty("GOOGLE_CLIENT_SECRET", Objects.requireNonNull(dotenv.get("GOOGLE_CLIENT_SECRET")));
        System.setProperty("GOOGLE_REFRESH_TOKEN", Objects.requireNonNull(dotenv.get("GOOGLE_REFRESH_TOKEN")));
        
        logger.info("Environment variables loaded successfully");

        SpringApplication.run(OmaStayApplication.class, args);
    }
}
