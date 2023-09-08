FROM openjdk:17

EXPOSE 8761

ADD target/naming-server.jar naming-server.jar

ENTRYPOINT [ "java","-jar","naming-server.jar"]