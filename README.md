#### Rock-Paper-Scissor Game

This project is based on Java Spring boot which is application to 
play game rock-paper-scissors

#### Technology used
- Java 8 : programming language 
- Spring boot : Web application 
- Maven : Building project
- Swagger : RESTful Documentation

#### Web Application configuration 
- Web Application is running on port `8181`
- You can change the configuration in `application.properties`
 
#### Steps to run the project

> Prerequisite
- Maven 
- Java 8 or higher 

> Start the application

Through following command:

    mvn clean install spring-boot:run 
    
Maven will install all the dependencies and followed by run the application

> To test the application
 
    http://localhost:8181/swagger-ui.html
    
    or hit these endpoints in postman
    http://localhost:8181/api/games?playerOneName=anup
    http://localhost:8181/api/games/11
    http://localhost:8181/api/games/11?choice=PAPER
    
    
#### Architectural points and Terminologies used in the project

> Game rules
 - Rock beats Scissors
 - Scissors beats Paper
 - Paper beats Rock

> Trying out and documentation for RESTful APIs

Swagger is already integrated and can be used for using API instead of curl. 

    http://localhost:8181/swagger-ui.html
    http://localhost:8181/v2/api-docs
