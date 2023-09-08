

# Image Microservice

 

This is a Simple Spring Boot application that helps to perform CRUD operations of Image database.

 

## How to Run

This application is packaged as a jar which has Tomcat 8 embedded. No Tomcat or JBoss installation is necessary. You run it using the ```java -jar``` command.

 

* Clone this repository

* Make sure you are using JDK 17 and Maven 3.2

* You can build the project and run the tests by running ```mvn clean package```

* Once successfully built, you can run the service by one of these two methods:

```
java -jar <filename.jar>

```

 

## About the Service

 

The service is a simple User REST service. It uses an MongoDb database to store the data.  

You can use this sample service to understand the conventions and configurations that allow you to create a DB-backed RESTful service. Once you understand and get comfortable with the sample app you can add your own services following the same patterns as the sample service.


### Get information about Images Data 

```
GET http://localhost:8088/image/

GET http://localhost:8080/image/{id}

```

 

### Create a New Customer

```

POST http://localhost:8088/image/save

Accept: application/json
Content-Type: application/json
{
    "id" : 12,
    "name" : "Image 1",
    "description" : "image data",
    "userid" : 4,
    "username" : "Srinivas",
    "password" : "1234",
    "file" : image.jpg

}
RESPONSE: HTTP 201 (Created)
```
### Update a User resource

```

PUT http://localhost:8088/image/{id}

Accept: application/json
Content-Type: application/json

{
    "id" : 12,
    "name" : "Image 1",
    "description" : "image data",
    "userid" : 4,
    "username" : "Srinivas",
    "password" : "1234",
    "file" : image.jpg
}

```

### Delete A Image from database 

```
DELETE http://localhost:8088/image/{id}

```

### Get All Images for a specific user from database 

```
GET http://localhost:8088/image/user/{userId}

```


### To view Swagger 2 API docs



Run the server and browse to localhost:8088/swagger-ui/index.html

 

# Running the project with MongoDb

 

This project uses MongoDb database so it is required to add MongoDb dependency into the pom.xml file in order to perform operations on the database.

Here is what you would do to back the services with MongoDb , for example:

 

### In pom.xml add:

 

```
       <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-mongodb</artifactId>
		</dependency>

```

 

### Append this to the end of application.properties:

 

```
spring.cloud.config.import-check.enabled=false
eureka.client.service-url.default-zone = http://localhost:8761/eureka

spring.application.name = image-data
server.port=8088
spring.data.mongodb.uri=mongodb://localhost:27017/testDb

spring.main.allow-bean-definition-overriding=true

spring.kafka.consumer.bootstrap-servers=localhost:9092
spring.kafka.consumer.group-id=imageData-group
spring.kafka.consumer.auto-offset-reset=earliest
spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
spring.kafka.consumer.value-deserializer=org.springframework.kafka.support.serializer.JsonDeserializer
spring.kafka.consumer.properties.spring.json.trusted=*
spring.kafka.consumer.properties.spring.json.trusted.packages=*

spring.kafka.producer.bootstrap-servers=localhost:9092
spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.value-serializer=org.springframework.kafka.support.serializer.JsonSerializer
```

 


 

# Questions and Comments: 2583863@tcs.com
