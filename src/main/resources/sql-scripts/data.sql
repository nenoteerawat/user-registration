INSERT INTO app_role (id, role_name, description) VALUES (1, 'STANDARD_USER', 'Standard User - Has no admin rights');
INSERT INTO app_role (id, role_name, description) VALUES (2, 'ADMIN_USER', 'Admin User - Has permission to perform admin tasks');



INSERT INTO app_user (id, first_name, last_name, username, password, address, phone, reference_code, salary, classification)
VALUES(1,'Teerawat', 'Yamsai', 'teerawat', 'd50c0334fd8550d9cc67e84de8ba7994bd09828d92d6c80c5cf23ee92519aebfd1e6f7421fbdda2c', '148 Kuchinarai district Kalasin province', '0995970594', '201809140594', '100000', 'Platinum');

INSERT INTO user_role(user_id, role_id) VALUES(1,1);
INSERT INTO user_role(user_id, role_id) VALUES(1,2);
