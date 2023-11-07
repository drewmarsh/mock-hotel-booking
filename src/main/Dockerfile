FROM openjdk:11-jre-slim

WORKDIR application

COPY ../../../target/D387_sample_code-0.0.2-SNAPSHOT.jar ./

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "387_sample_code-0.0.2-SNAPSHOT.jar"]