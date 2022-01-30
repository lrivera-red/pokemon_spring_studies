FROM alpine:latest
RUN apk update && apk add openjdk11
WORKDIR /app
COPY build/libs/PokemonEstudoApplication-0.0.1.jar /app/app.jar
EXPOSE 8080
ADD data.sql /app/resources
CMD ["java","-jar","app.jar"]