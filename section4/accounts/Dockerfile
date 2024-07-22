# Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim

# Information around who maintains the image
LABEL email = rlarkqlskb@naver.com
LABEL name = "rlarkqlskb"
LABEL version = "s4"
LABEL description = "docker practice by using spring boot"

# Add the application's jar to the images
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

# execute the application
ENTRYPOINT ["java", "-jar", "accounts-0.0.1-SNAPSHOT.jar"]
