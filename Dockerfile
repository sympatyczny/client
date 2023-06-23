FROM amazoncorretto:17-alpine3.12-jdk as builder
WORKDIR /opt/app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY ./src ./src
RUN ./mvnw clean install

FROM amazoncorretto:17-alpine
WORKDIR /opt/app
COPY --from=builder /opt/app/target/*.jar /opt/app/rabbitmq-client.jar
EXPOSE 9090
CMD java -jar *.jar --env=prod