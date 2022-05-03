# nagel
# The solution has two repositories 
# 1) NagelTask - Spring Boot project with Spring Security
# 2) my-first-app - React js project 

## 1) NAGELTASK
  1) Open the project in Intellij Idea 2020
  2) Install docker desktop 
  3) Open git bash and change directory to NagelTask Project run below command
      cd NagelTask 
      docker-compose up
     - This will install postgress in your machine with user id and password mentioned in docker-compose.yml file.

  4) Download DBeaver and connect to database with below parameters
      Host : localhost
      Port : 5433
      database: postgres
      username: postgres, password: postgres
  5) run gradle build in Intellij tool
  6) run gradle->Tasks-> application -> bootRun
  
  This should import all the data from the csv and save to database city table.
  
  7) At this point if the database is refreshed we should be able to see three tables
     1) city, 2) role and 3) users
     
      # if table role doesnot exist then run the below query 
              CREATE TABLE "role" (
                    id serial4 NOT NULL,
                    "name" varchar(20) NULL,
                    CONSTRAINT role_pkey PRIMARY KEY (id)
                  );
      # if table users doesnot exist then run the below query 
            CREATE TABLE users (
                id bigserial NOT NULL,
                "password" varchar(255) NULL,
                username varchar(255) NULL,
                "role" varchar(255) NULL,
                CONSTRAINT ukr43af9ap4edm43mmtq01oddj6 UNIQUE (username),
                CONSTRAINT users_pkey PRIMARY KEY (id)
              );
   8) Insert data in role Table 
        
        INSERT INTO "role" (id, "name") VALUES(1, 'ROLE_USER');
        INSERT INTO "role" (id, "name") VALUES(2, 'ROLE_ADMIN');
        INSERT INTO "role" (id, "name") VALUES(3, 'ROLE_ALLOW_EDIT');

  ## THE APPLICATION NAGELTASK SHOULD BE UP AND RUNNING SUCCESSFULLY WITHOUT ANY ISSUES WITH THE CHANGES.

# 2)FrontEnd -  my-first-app (Apologies for the name my-first-app) 

1) Clone the project 
2) Change the directory to my-first-app
3) run npm install
4) npm start

![image](https://user-images.githubusercontent.com/60135402/166426148-f1b25158-fa23-4667-8251-ee6d3f355f49.png)


5) So I have created two users 
    1) username : devedit
      password : devedit
      role : ROLE_ALLOW_EDIT
     
    2) username : dev
      password : dev
      role : ROLE_USER
      
      User name "devedit" will be able to see the action button for edit. The user name "dev" will not be able to see the button for edit in the table.
  
  We can change the user from the constants.js file 
  export const properties = {
    username: "devedit",
    password: "devedit"
};


(DUE TO LACK OF TIME, I COULD NOT CREATE A LOGIN AND REGISTRATION PAGE. HOWEVER, THE PURPOSE OF ROLE BASED ACTION CAN BE SIMULATED WITH THE HELP OF THE FILE constants.js BY JUST CHANGING THE USERNAME AND PASSWORD TO THE DESIRED ONE.)

    
## Screen shots

![image](https://user-images.githubusercontent.com/60135402/166429942-15c84b6c-1630-4c4d-b21c-87cd3e3f51cd.png)

# Search

![image](https://user-images.githubusercontent.com/60135402/166430049-f0d72d99-30fe-4b9a-97f1-0335a69c4ab0.png)


# Edit City Name

![image](https://user-images.githubusercontent.com/60135402/166430205-b115f691-9572-4c63-bc79-8ea081264894.png)


# Error Message

  ![image](https://user-images.githubusercontent.com/60135402/166430470-faffd16c-029a-47a0-96d0-e0006ac6936a.png)
  ![image](https://user-images.githubusercontent.com/60135402/166430664-91c6055c-ae04-421d-aa3a-e6c4643f2f32.png)

# Error Message with Wrong user Credentials
![Uploading image.png…]()






