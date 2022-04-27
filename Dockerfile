FROM maven:3.8.1-openjdk-15-slim AS builder

#COPY . /app
#COPY ./m2/settings.xml /root/.m2/settings.xml
#
#WORKDIR app/
#
#RUN mvn package -DskipTests=true
#RUN mkdir /opt/jar && mv ./application/target/sqs-consumer-*.jar /opt/jar/application.jar
#
#COPY pom.xml /opt/jar/
#
#RUN rm -rf /app

COPY ./application/target/*.jar /opt/jar/application.jar

FROM azul/zulu-openjdk-alpine:15

RUN mkdir /opt/jar

COPY --from=builder /opt/jar/* /opt/jar/


USER root

RUN apk add --upgrade libx11
RUN echo "America/Sao_Paulo" > /etc/timezone

ENV TZ America/Sao_Paulo
ENV LANG pt_BR.UTF-8
ENV LANGUAGE pt_BR:pt_br
ENV LC_ALL pt_BR.UTF-8

ENV JVM_XMS=128m
ENV JVM_XMX=256m

WORKDIR /opt/jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "application.jar"]