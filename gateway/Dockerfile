FROM amazoncorretto:11-alpine-jdk
COPY target/*.jar /kanban-gateway.jar
ENTRYPOINT ["java", "-jar", "/kanban-gateway.jar"]