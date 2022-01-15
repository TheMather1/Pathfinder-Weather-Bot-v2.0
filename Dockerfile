FROM gradle:jdk16
RUN gradle build
COPY build/libs/weatherBot-2.0.jar /app/app.jar
