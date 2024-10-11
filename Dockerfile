FROM gradle:7.6-jdk as builder

WORKDIR /app
COPY . ./
RUN gradle clean build --no-daemon

FROM openjdk:17-alpine

WORKDIR /app

COPY --from=builder /app/build/libs/OmaStay-0.0.1-SNAPSHOT.jar .

COPY nginx/ssl/accounts /etc/nginx/ssl/accounts

COPY ./install_certbot.sh /app/install_certbot.sh
RUN chmod +x /app/install_certbot.sh

# install_certbot.sh 파일 실행
RUN sh /app/install_certbot.sh

RUN chown -R root:root /etc/nginx/ssl/accounts && chmod -R 755 /etc/nginx/ssl/accounts

EXPOSE 9090

ENTRYPOINT ["java", "-jar", "OmaStay-0.0.1-SNAPSHOT.jar"]
