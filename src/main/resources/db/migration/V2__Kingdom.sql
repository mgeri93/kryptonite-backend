CREATE TABLE kingdom
(
    id                  INT AUTO_INCREMENT PRIMARY KEY,
    name                VARCHAR(50),
    application_user_id INT,
    CONSTRAINT application_user_id FOREIGN KEY (application_user_id) REFERENCES application_user(id))