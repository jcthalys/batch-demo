DROP TABLE IF EXISTS PEOPLE;

CREATE TABLE PEOPLE (
  ID          BIGINT(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  FIRST_NAME  VARCHAR(255) NOT NULL,
  AGE         INT(3) NOT NULL,
  EMAIL       VARCHAR(255) NOT NULL
);