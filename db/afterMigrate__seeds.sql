-- TRUNCATE TABLE users;
INSERT INTO user(id, username, email, first_name, last_name, password, role)
VALUES
(1,'superadmin', 'admin@mail.com', 'admin', 'adminov','$2y$12$xSA2vSxfw8JONfp/0GbiMe52MRnkSC4J7hm7/aL2EsQP5gsP3zSNS', 'ADMIN' ),
(3,'billondop', 'pm@mail.com', 'Bill', 'Clinton','$2y$12$xSA2vSxfw8JONfp/0GbiMe52MRnkSC4J7hm7/aL2EsQP5gsP3zSNS', 'PM' ),
(5,'bigfrantz', 'pm2@mail.com', 'Frantz', 'Ferdinand','$2y$12$xSA2vSxfw8JONfp/0GbiMe52MRnkSC4J7hm7/aL2EsQP5gsP3zSNS', 'PM' ),
(6,'mariamira', 'pm3@mail.com', 'Monica', 'Beltran','$2y$12$xSA2vSxfw8JONfp/0GbiMe52MRnkSC4J7hm7/aL2EsQP5gsP3zSNS', 'PM' ),
(7,'francesca', 'pm4@mail.com', 'Francesca', 'Stravoni','$2y$12$xSA2vSxfw8JONfp/0GbiMe52MRnkSC4J7hm7/aL2EsQP5gsP3zSNS', 'DEV' );

INSERT INTO project(id, name, code, user_id)
VALUES
(1,'PharmaCom', 'ABCD1',3  ),
(3,'Tracom', 'IUHU2', 3 ),
(5,'Facephone', 'NUKA4', 7 ),
(6,'BlueDog', 'IGQQ3',7 ),
(7,'WatchCat', 'VISA2',7 );

insert into task(id,description, progress, status, deadline, user_id, project_id)
values
(1,'hard task',33,'NEW',null,7,3),
(2,'very hard task',44,'NEW',null,7,3),
(3,'ultra hard task',55,'NEW',null,3,3);
