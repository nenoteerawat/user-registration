CREATE TABLE app_role
(
	id INTEGER NOT NULL AUTO_INCREMENT,
	role_name VARCHAR(100) NOT NULl,
	description VARCHAR(100) NOT NULL,
	PRIMARY KEY(id)
);
CREATE TABLE app_user
(
   id INTEGER NOT NULL AUTO_INCREMENT,
   first_name VARCHAR(255) NOT NULL,
   last_name VARCHAR(255) NOT NULL,
   username VARCHAR(100) NOT NULL,
   password VARCHAR(100) NOT NULL,
   address VARCHAR(255) NOT NULL,
   phone VARCHAR(10) NOT NULL,
   reference_code VARCHAR(12) NOT NULL,
   salary INTEGER NOT NULL,
   classification VARCHAR(50) NOT NULL,
   PRIMARY KEY(id),
);
CREATE TABLE user_role (
  user_id bigint(20) NOT NULL,
  role_id bigint(20) NOT NULL,
  CONSTRAINT fk_app_user FOREIGN KEY (user_id) REFERENCES app_user (id),
  CONSTRAINT fk_app_role FOREIGN KEY (role_id) REFERENCES app_role (id)
);