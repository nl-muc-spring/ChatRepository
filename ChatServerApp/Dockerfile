FROM gradle:jdk8-alpine as builder

COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle --no-daemon clean build

FROM openjdk:8-jre-alpine
EXPOSE 8000
RUN mkdir /app
COPY --from=builder /home/gradle/src/build/libs/chat-server-app-0.1.0.jar /app/app.jar
WORKDIR /app
CMD java -jar app.jar

