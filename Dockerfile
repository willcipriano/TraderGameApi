FROM amazoncorretto:11-alpine
COPY . /
RUN apk add maven
RUN mvn install
ENTRYPOINT ./mvnw spring-boot:run
