<h1 align="center"><strong>DockerHub + Github Actions</strong></h1>

Description

```
version: '3.9'
services:
  database:
    container_name: mysql
    image: mysql:8.0
    command: mysqld --default-authentication-plugin=mysql_native_password
    restart: always
    environment:
      TZ: America/Sao_Paulo
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: spring_boot_api
      MYSQL_ROOT_HOST: '%'
      MYSQL_TCP_PORT: 5001
    ports:
      - 5001:5001
    networks:
      - spring-boot-network
  spring-boot-api:
    container_name: spring-boot-api
    image: bladerunner9040/spring-boot-api-with-dockerhub-githubactions
    restart: always
    environment:
      TZ: America/Sao_Paulo
      SPRING.DATASOURCE.URL: jdbc:mysql://database:5001/spring_boot_api?createDatabaseIfNotExist=true&useTimezone=true&serverTimezone=UTC
      SPRING.DATASOURCE.USERNAME: root
      SPRING.DATASOURCE.PASSWORD: root
    ports:
      - 5000:5000
    command: mvn spring-boot:run
    depends_on:
      - database
    networks:
      - spring-boot-network
networks:
  spring-boot-network:
    name: spring-boot-network
    driver: bridge
```

&nbsp;

## **Dependencies**

- Docker
- Docker compose
- Java JDK 17
- Maven 3.9.3+