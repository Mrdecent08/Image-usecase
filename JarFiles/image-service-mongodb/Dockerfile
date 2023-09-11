FROM openjdk:17

EXPOSE 8761

ADD target/image-service.jar image-service.jar

ENTRYPOINT [ "java","-jar","image-service.jar"]