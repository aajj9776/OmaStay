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

# SSL 인증서 관련 부분 제거

# 포트 노출
EXPOSE 9090

# 어플리케이션 실행
ENTRYPOINT ["java", "-jar", "OmaStay-0.0.1-SNAPSHOT.jar"]
