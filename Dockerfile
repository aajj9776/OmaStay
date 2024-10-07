FROM gradle:7.6-jdk as builder

WORKDIR /app
COPY . ./
RUN gradle clean build --no-daemon

FROM openjdk:17-alpine
WORKDIR /app
COPY --from=builder /app/build/libs/OmaStay-0.0.1-SNAPSHOT.jar .
EXPOSE 9090
ENTRYPOINT ["java","-jar","OmaStay-0.0.1-SNAPSHOT.jar"]
