FROM openjdk:8
ADD target/stockservice-0.0.1-SNAPSHOT.jar stockservice.jar
EXPOSE 9192
ENTRYPOINT ["java","-jar","stockservice.jar"]
