FROM openjdk:8-jdk-alpine

LABEL maintainer="thiago.alessandro.farias@gmail.com"

ENV TZ=America/Belem
ENV APP_FILE=monitoramento-rede.jar
ENV APP_DIR=/tmp/app/

VOLUME /tmp

ADD speedtest /tmp/app/speedtest
ADD speedtest.5 /tmp/app/speedtest.5
ADD speedtest.md /tmp/app/speedtest.md

ADD target/${APP_FILE} ${APP_DIR}/${APP_FILE}

ENTRYPOINT java -jar ${APP_DIR}/${APP_FILE}
