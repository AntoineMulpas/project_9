**Project 9 - Openclassrooms - MédiLabo Solutions:**

Stack:
- Spring Boot
- Spring Security
- Spring Data JPA
- Postgres
- MongoDB
- Spring cloud suite
  - Spring Cloud Gateway
  - Spring Config
  - Eureka
- Spring Actuator
- Lombok
- React
- Docker

**How to run the project:**

1. Download zip file from the GitHub repository and unzip.
2. From the terminal, CD in the root of the unzipped directory.
3. Run in the terminal the following command : 'docker compose up'
   Wait for the Docker's images to build, containers will run automatically once done. 
4. When all the containers are up and running, in your navigator go to 'http://localhost:3000/'
5. To log in in the app, use the following:
   username: antoine
   password: password123
6. Once you logged, you can add a patient, add note to his file etc.

**App architecture:**   

![p9_architecture](https://github.com/AntoineMulpas/project_9/assets/91942255/e01bbb95-e704-4223-ba48-1b4e3f2e8574)

- Frontend is achieved using React. 
 API Gateway: is the entrypoint of the backend, all requests go throught it. 
  - to access the content, you should be authenticated. This service talks to the authentication service.
  - if a user is logged, then it communicates with the Eureka service to fetch the instance of the patient, note and evaluation services.
  - if not logged, the access is denied.
  - responsible for Load Balancing.
- Service authentication: responsible to authenticate a user. Implements JWT.
- Service cloud config: responsible to fetch application-properties from github, stored at https://github.com/AntoineMulpas/microservice-config-repo
- Service Eureka: register each instance of service, therefore enable service discovery.
- Service patient: responsible for the patient business, add, update, retrieve and delete from Postgres DB.
- Service note: responsible for the note business, add, delete and retrieve from MongoDB.
- Service evaluation: responsible to analyse notes from a patient file and calculate the risks associated.

**Green code:**

To achieve this goal, we may for instance update a table each time a note is written by a doctor. This table would be holding data such as the number of risks, and the associated label. It would reduce the number of reading of all notes to determine the risk. 


**Note on repositories:**
- for the sake of simplicity, I have grouped all services in one repository. But while coding this app, I used several repositories. Here the links to the others to show that it is not one commit :
  - https://github.com/AntoineMulpas/microservice_notes
  - https://github.com/AntoineMulpas/p9_microservice_patient
  - https://github.com/AntoineMulpas/microservice-config-repo
  
