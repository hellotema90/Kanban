FROM amazoncorretto:11-alpine-jdk
COPY target/*.jar /kanban-server.jar
ENTRYPOINT ["java", "-jar", "/kanban-server.jar"]