# syntax = docker/dockerfile:1.2
FROM gradle:jdk21 AS BUILD
ARG GITHUB_TOKEN
WORKDIR /usr/app/
COPY . .
ENV GITHUB_TOKEN=$GITHUB_TOKEN
RUN gradle build

FROM eclipse-temurin:21
ARG baseUrl
ARG pathfinder_weather_bot_token
ARG spring_datasource_password
ARG spring_datasource_url
ARG spring_datasource_username
ARG spring_security_oauth2_client_registration_discord_client-secret
VOLUME /tmp
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY --from=BUILD $APP_HOME .
ENV baseUrl=$baseUrl
ENV pathfinder_weather_bot_token=$pathfinder_weather_bot_token
ENV spring_datasource_password=$spring_datasource_password
ENV spring_datasource_url=$spring_datasource_url
ENV spring_datasource_username=$spring_datasource_username
ENV spring_security_oauth2_client_registration_discord_client-secret=$spring_security_oauth2_client_registration_discord_client-secret
ENTRYPOINT exec java -jar $APP_HOME/build/libs/weatherBot-2.0.jar

