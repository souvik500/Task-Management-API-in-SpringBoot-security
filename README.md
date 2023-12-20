ask Management API(Basic Design-SignIn, SignUp, signout, user and Admin controller)
Blogging Platform Logo

Table of Contents
Introduction
Technologies & Framework Used
Features
Prerequisites
Installation
Usage
API Endpoints
Data Structures
Security
Project Summary
Contributing
License
Introduction
Task Management System API is a backend project that aims to provide a robust and scalable platform for users to create and manage Tasks, update on tasks, delte the tasks, and authorize user and Adming can control all Task Management System. The platform is designed to offer a seamless user experience and allow tasks to showcase their writing skills and engage with a wider audience.

Framework and Technologies Used :
Java Spring Boot: For building the backend server and managing RESTful endpoints.

MySQL: As the relational database management system to store user and blog-related data.

Hibernate: For object-relational mapping between Java entities and the MySQL database.

Swagger: For API documentation and testing.

Maven: As the build tool to manage dependencies and run tasks.

Framework : SpringBoot
Technologies : Java, MySQL
Others : Java Persistence API (JPA), Swagger UI, Email
Features
User Registration and Authentication: Users can sign up, log in, and log out securely using email and password credentials.

Admin Registration and Authentication: Admin can sign up, log in, and log out securely using email and password credentials and Admin can also control all activity of any user.

Create and Manage Tasks: Authenticated users can create new Tasks, edit their existing task, and delete their tasks.

Pagination: Tasks are paginated to enhance the user experience.

User-Friendly API: The project provides a well-documented and user-friendly API for easy integration with front-end applications.

Prerequisites :
To run this project, ensure that you have the following installed:

Java Development Kit (JDK)
MySQL
Maven
Installation :
Clone the repository from GitHub link.
Install Java JDK and Maven on your machine.
Set up a MySQL database and configure the database connection in the application.properties file.
Run the Maven build to compile the project.
Start the application using the command.
Usage
After starting the application, access the API documentation at http://localhost:8080/swagger-ui.html for information on available endpoints and how to interact with the API.

Use tools like Postman or any API client to test the various API endpoints.

Data Flow
API Endpoints Used:
1. Controller (User, Task, AuthenticationToken):-
User/Task Controller :-
POST /user/signUp - Register a new user with the system.
POST /user/signIn - Authenticate and obtain a token for the Tasks user and By Hashing Password and Using Email
POST /user/signOut - Log out a user.
POST /task/add - Create a new Tasks post.
DELETE /task/undone - Delte a Task by adimin or user.
POST /task/done - Add a task as done
GET /task/todo - Get all tasks of a particular user
PUT /task/update - Update by admin or user any tasks information
POST /task/taskId/pagination - get paginated tasks of a user
Admin Controller :-
POST /admin/signUp - Register a new admin with the system.
POST /admin/signIn - Authenticate and obtain a token for the Tasks admin and By Hashing Password and Using Email
POST /admin/signOut - Log out a admin.
2. DataBase Design:-
Database Used :- SQL/Hibernate Database using
The database schema consists of the following tables:

User: Stores user details like name, email, and password.
Post: Contains task information, including title, content, and user author.
Task: Stores Task name, task date along with the user who made the task.
AuthenticationToken: Manages user authentication tokens.
3. Model / Entity(User, Task, Admin, AuthenticationToken):-
Annotation Used :- @Data, @NoArgsConstructor, @AllArgsConstructor, @Id, @OneToOne, @Column, @JoinColumn, @Entity, @GeneratedValue, @Enumerated, @NotBlank, @Min, @Max, @ManyToMany, @ManyToOne, @JoinTable, @JsonProperty, @NotNull, @Column, @Validated, @Pattern
Data Structure used in my Project
Used :- SQL Database --> But Mostly used Java Concept, oops, collection, ENUM
Security
Authentication is implemented using an Authentication Token class. This token is generated upon successful sign-in and must be included in the headers of subsequent requests to authorized endpoints.

Encryption
Authentication is implemented using an Authentication Token class. This token is generated upon successful sign-in and must be included in the headers of subsequent requests to authorized endpoints.

Project Summary
Aim :- This is basically good project for learning purpose springBoot basics, Mappings, Annotation, API, spring mvc and CRUD Operation, swagger, crud Repository inbuilt method, and Custom Finder and Custom Query. In this project i just add Posts, get all psots a particular user, update User inforamtion lot of things i learned from this project.
👨‍💻 Sameer
Twitter: @Sameer.twitter

Github: @Sameer-Github

🤝 Contributing

Contributions, Thanks to everyone , contributing with me and know about more myself visit my profile.

Show Your Support

Give a ⭐if this project helped you!

BECOME A SOFTWARE DEVELOPER 👩‍💻
📝 License
