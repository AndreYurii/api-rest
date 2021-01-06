FROM openjdk:11
COPY ./target/apirest-0.0.1-SNAPSHOT.jar /temp/app.jar
WORKDIR /temp
ENTRYPOINT ["java", "-jar", "app.jar"]