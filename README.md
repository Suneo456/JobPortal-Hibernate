# Job Portal - JDBC CRUD

A simple Job Portal database project implemented using Core Java, JDBC, Maven and MySQL.

## Database

Database name: `JOB_PORTAL`

Tables:
1. `users`
2. `job_post`
3. `job_tech_stack`

Relationships:
- One user can create many job posts.
- One job post can have many technologies.

## CRUD Operations

### Users
- Create user
- Read all users
- Read user by ID
- Update user
- Delete user

### Job Posts
- Create job post
- Read all job posts
- Read job post by ID
- Update job post
- Delete job post

### Job Tech Stack
- Add technology
- Read all technologies
- Read technologies by post ID
- Update technology
- Delete technology

## JDBC Concepts Used

- DriverManager
- Connection
- PreparedStatement
- ResultSet
- SQLException
- try-with-resources
- DAO pattern

## Requirements

- JDK 8 or higher
- Maven
- MySQL

## Setup

1. Create the database and tables using `database/schema.sql`.
2. Open `src/main/java/com/jobportal/DBConnection.java`.
3. Change the MySQL username and password.
4. Run `Main.java`.

## Important

The database schema uses manually supplied primary-key IDs, matching the supplied project schema. Do not change the table/column names unless you also update the Java code.

For a real production application, passwords should be hashed instead of stored as plain text. This assignment keeps the password field aligned with the supplied schema.
