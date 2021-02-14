FROM amazoncorretto:11-alpine as build
ARG ALPHA_API_KEY
COPY . /
RUN sed -i 's/ALPHAAPIKEYGOESHERE/$ALPHA_API_KEY/' src/main/java/rest/trader/traderapi/utility/ApiKeyService.java
    && apk add maven
ENTRYPOINT ["./mvnw"]
CMD ["-P test-then-run"]