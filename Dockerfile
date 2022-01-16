FROM adoptopenjdk/openjdk11:alpine
WORKDIR /app
COPY build/libs/PokemonEstudoApplication-0.0.1.jar /app/app.jar
EXPOSE 8080
CMD ["java","-jar","app.jar"]
ADD data.sql /app/resources