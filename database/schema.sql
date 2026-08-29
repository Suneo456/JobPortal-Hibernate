CREATE DATABASE IF NOT EXISTS JOB_PORTAL;
USE JOB_PORTAL;

DROP TABLE IF EXISTS job_tech_stack;
DROP TABLE IF EXISTS job_post;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id INT NOT NULL,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE job_post (
    post_id INT NOT NULL,
    user_id INT NOT NULL,
    post_profile VARCHAR(255) NOT NULL,
    post_desc TEXT,
    req_experience INT,
    PRIMARY KEY (post_id),
    CONSTRAINT fk_job_post_user
        FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE job_tech_stack (
    tech_id INT NOT NULL,
    post_id INT NOT NULL,
    technology VARCHAR(100) NOT NULL,
    PRIMARY KEY (tech_id),
    CONSTRAINT fk_tech_stack_post
        FOREIGN KEY (post_id) REFERENCES job_post(post_id)
);
