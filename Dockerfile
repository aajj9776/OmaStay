# Gradle 빌드 환경 설정 (빌드 단계)
FROM gradle:7.6-jdk as builder

# 작업 디렉토리 설정
WORKDIR /app

# 소스 코드 복사
COPY . ./

# .env 파일 복사
COPY .env .env
# .env 파일 로드
RUN echo "source /app/.env" >> ~/.bashrc

# Gradle 빌드 실행 (의존성 해결 및 빌드)
RUN gradle clean build --no-daemon

# 작업 디렉토리 설정
WORKDIR /app

# .env 파일 복사
COPY .env .env
# 빌드된 JAR 파일을 빌드 단계에서 복사
COPY --from=builder /app/build/libs/OmaStay-0.0.1-SNAPSHOT.jar .

# 환경 변수 설정
ENV SPRING_DATASOURCE_URL=${DB_URL}?useSSL=false&allowPublicKeyRetrieval=true
ENV SPRING_DATASOURCE_USERNAME=${DB_USERNAME}
ENV SPRING_DATASOURCE_PASSWORD=${DB_PASSWORD}
# 포트 9090 노출
EXPOSE 9090

# Spring Boot 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "OmaStay-0.0.1-SNAPSHOT.jar"]
