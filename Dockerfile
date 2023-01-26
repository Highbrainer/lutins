FROM maven as build
WORKDIR /app
COPY . .
#RUN mvn clean package -DskipTests
#

FROM bellsoft/liberica-openjdk-alpine:17
#ARG JAR_FILE="/app/target/*.jar"
WORKDIR /root
COPY --from=build /app/target/demo-*.jar /root/application.jar
ENTRYPOINT ["java", "-jar", "/root/application.jar"]