FROM gradle:7.6-jdk as builder

WORKDIR /app
COPY . ./
RUN gradle clean build --no-daemon

# 실행을 위한 OpenJDK 이미지 사용
FROM openjdk:17-alpine

# 작업 디렉토리 설정
WORKDIR /app

# 빌드된 JAR 파일 복사
COPY --from=builder /app/build/libs/OmaStay-0.0.1-SNAPSHOT.jar .

# 필요한 디렉토리 복사
COPY nginx/ssl/accounts /etc/nginx/ssl/accounts

# install_certbot.sh 파일 복사 및 권한 부여
COPY ./install_certbot.sh /app/install_certbot.sh
RUN chmod +x /app/install_certbot.sh

# install_certbot.sh 파일 실행
RUN sh /app/install_certbot.sh

# 소유자 변경 및 권한 수정
RUN chown -R root:root /etc/nginx/ssl/accounts && chmod -R 755 /etc/nginx/ssl/accounts

# 포트 노출
EXPOSE 9090

# 어플리케이션 실행
ENTRYPOINT ["java", "-jar", "OmaStay-0.0.1-SNAPSHOT.jar"]
