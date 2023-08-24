FROM eclipse-temurin:17-jdk-alpine
LABEL maintainer="uiratan@gmail.com"
ENV LANG C.UTF-8
RUN apk add --update bash
ADD build/libs/*.jar /app/app.jar
EXPOSE 8080
CMD java $JAVA_OPTIONS -jar /app/app.jar $APP_OPTIONS