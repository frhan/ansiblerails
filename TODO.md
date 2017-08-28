
Library
-------
* logger
* 

Ref
---
* https://github.com/bfwg/springboot-jwt-starter
* https://github.com/mrin9/Angular-SpringBoot-REST-JWT
* http://blog.christianposta.com/microservices/the-hardest-part-about-microservices-data/

Spring Boot Docker MySQL Demo
-----------------------------
* https://github.com/jiwhiz/spring-boot-docker-mysql/wiki

APP
---
* https://github.com/WolfAlexander/TheRecruitmentSystem
* https://github.com/spring-petclinic/spring-petclinic-microservices
* https://github.com/sqshq/PiggyMetric
* https://github.com/altfatterz/spring-boot-flyway
* https://severalnines.com/blog/mysql-docker-containers-understanding-basics
* http://g00glen00b.be/spring-data-jpa/ 
* https://g00glen00b.be/docker-spring-boot/
* https://spring.io/guides/gs/spring-boot-docker/

*** https://github.com/altfatterz/dockerized-microservices 


docker run -d \
    --name demo-mysql \
    -e MYSQL_ROOT_PASSWORD=p4SSW0rd \
    -e MYSQL_DATABASE=demo \
    -e MYSQL_USER=dbuser \
    -e MYSQL_PASSWORD=dbp4ss \
    mysql:latest
    
    


docker run --name demo-mysql -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=spjwt -e MYSQL_USER=farhan -e MYSQL_PASSWORD=cefalo -p 3306:3306 -d mysql:5.6

