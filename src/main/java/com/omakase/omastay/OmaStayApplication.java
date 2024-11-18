package com.omakase.omastay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OmaStayApplication {

    Dotenv dotenv = Dotenv.configure().load();
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

    public static void main(String[] args) {
        SpringApplication.run(OmaStayApplication.class, args);

    }
}
