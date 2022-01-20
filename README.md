# Thesis “Cloud storage”
[Russian language](README_RU.md)
## Project Description

This application is the back-end part of the REST service.
The service provides a REST interface for uploading files and displaying
a list of the user's already uploaded files. All requests to the service
are authorized. A pre-prepared
[web application (FRONT)](https://github.com/netology-code/jd-homeworks/tree/master/diploma/netology-diplom-frontend)
to the developed BACK service without modifications, and the FRONT functionality
is also used to authorize, download and display a list of user files.

## Launch

The application is launched with the `docker-compose up` command.
But first you need to get the front, mysql and back images. 

**mysql**: Download the image using the `docker pull mysql` command

**back**: Using [Dockerfile](Dockerfile) and the command `docker build -t back .`,
we create the back image

**front**: You need to add a Dockerfile to the root of the project with the FRONT application. 
Next, write the command `docker build -t front .`. Below is the contents of the Dockerfile:
```Dockerfile
FROM node:12
COPY . .
EXPOSE 8080
CMD [ "npm", "run", "serve" ]
```
The app is running at [http://localhost:8080](http://localhost:8080).

## Application operation

The application is implemented using the Spring framework.

- The service performs all the methods described in
  [yaml file](https://github.com/netology-code/jd-homeworks/blob/master/diploma/CloudServiceSpecification.yaml):
    1. Displaying a list of files
    2. Adding a file
    3. Deleting a file
    4. Authorization
- Information about users of the service (logins for authorization) is stored in the MySQL database
- Logging is done in cloud.log

After the `docker-compose up` command, the service starts running at
[http://localhost:8080](http://localhost:8080). 
You will first be redirected to a page where you enter your email and password.

All users and their roles are stored in the database. They are registered at the first start with
the help of **liquibase**. Passwords are encrypted with BCryptPasswordEncoder. Test user:
login = lev@mail.ru, password = 1234. Login and password are checked on the BACK side.
Upon successful verification, the configured **JsonWebToken** is sent back and stored on BACK
([job requirements](https://github.com/netology-code/jd-homeworks/blob/master/diploma/cloudservice.md)).
All further requests are made with this token in the Header auth-token.
After logout, the token is deleted

Files, like users, are stored in a MySQL database.