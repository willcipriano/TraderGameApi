FROM amazoncorretto:11-alpine as build
COPY . /
RUN apk add maven
ENTRYPOINT ["./mvnw"]
CMD ["test-then-run"]