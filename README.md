[![CircleCI](https://circleci.com/gh/mountkingx/spring-mongodb-blog/tree/main.svg?style=svg)](https://circleci.com/gh/mountkingx/spring-mongodb-blog/tree/main)

# spring-mongodb-blog
Personal development practice

### Project Initialization

Generated from https://start.spring.io/
- spring boot 2.4.12
- gradle
- java
- jar
- jdk 11

Packages:
- Lombok
- Spring Configuration Processor
- Thymeleaf
- Spring Data MongoDB
- Spring Web
- Spring Boot DevTools


gradle 7.2 is recommended

### Mongodb Atlas cluster
aws | us-east-1 (cluster-2021-oct-26)

### First Commit
- manually added checkstyle configuration
- visit: http://localhost:8080/api/health

### CircleCI Commit
- incorporated circleci support (.circleci/config)

### Dao Layer Testing

spring-boot-starter-data-mongodb enabled Spring support for MongoDB:
```xml
<dependency>
    <groupId>de.flapdoodle.embed</groupId>
    <artifactId>de.flapdoodle.embed.mongo</artifactId>
    <scope>test</scope>
</dependency>
```

### Info
- https://app.circleci.com/settings/project/github/mountkingx/spring-mongodb-blog
- https://dashboard.heroku.com/apps/afternoon-shore-94848
- https://afternoon-shore-94848.herokuapp.com/
