# Gradle 빌드 환경 설정 (빌드 단계)
FROM gradle:7.6-jdk as builder

# 작업 디렉토리 설정
WORKDIR /app

# 소스 코드 복사
COPY . ./

# 환경 변수 출력 (빌드 단계에서 로그로 확인)
RUN echo "DB_URL=${DB_URL}" >> /var/log/env_vars.log
RUN echo "MAIL_USERNAME=${MAIL_USERNAME}" >> /var/log/env_vars.log
RUN echo "JWT_SECRET_KEY=${JWT_SECRET_KEY}" >> /var/log/env_vars.log

# Gradle 빌드 실행 (의존성 해결 및 빌드)
RUN gradle clean build --no-daemon

# 실행을 위한 OpenJDK 이미지 사용 (실행 단계)
FROM openjdk:17-alpine

# 작업 디렉토리 설정
WORKDIR /app

# 빌드된 JAR 파일을 빌드 단계에서 복사
COPY --from=builder /app/build/libs/OmaStay-0.0.1-SNAPSHOT.jar .

# 포트 9090 노출
EXPOSE 9090

# Spring Boot 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "OmaStay-0.0.1-SNAPSHOT.jar"]
