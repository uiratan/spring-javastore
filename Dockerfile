FROM eclipse-temurin:17-jdk-alpine
LABEL maintainer="uiratan@gmail.com"

ENV LANG C.UTF-8

RUN apk add --update bash

COPY docker-entrypoint.sh /usr/local/bin
ENTRYPOINT ["docker-entrypoint.sh"]

ADD build/libs/*.jar /app/app.jar

EXPOSE 8080

HEALTHCHECK --start-period=10s --timeout=3s CMD wget -q -O /dev/null http://localhost:8080/actuator/health || exit 1

CMD java $JAVA_OPTIONS -jar /app/app.jar $APP_OPTIONS