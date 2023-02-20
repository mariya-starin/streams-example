#FROM ubuntu
#RUN apt-get update && apt-get install -y iputils-ping
#CMD bash

FROM gradle:5.2.1-jdk11-slim AS builder
WORKDIR /Users/mstarin/streams-example/src/main/java/consumer
COPY build.gradle ./
COPY . .
USER root
RUN gradle build
RUN ls /Users/mstarin/streams-example/src/main/java/consumer/build/distributions/

FROM openjdk:11.0.2-jre-slim
COPY --from=builder /Users/mstarin/streams-example/src/main/java/consumer/build/distributions/consumer.tar /app/
WORKDIR /app
RUN tar -xvf consumer.tar

WORKDIR /app/consumer

CMD sleep 500000
#CMD java -jar lib/views-consumer.jar clients.PageviewsConsumer.java

