-- TRUNCATE TABLE users;
INSERT INTO users(id, username, email, first_name, last_name, password, role)
VALUES
(1,'ananas', 'admin@mail.ru', 'admin', 'adminov','$2y$12$xSA2vSxfw8JONfp/0GbiMe52MRnkSC4J7hm7/aL2EsQP5gsP3zSNS', 'ADMIN' ),
(3,'pm', 'pm@mail.ru', 'pm', 'pmov','$2y$12$xSA2vSxfw8JONfp/0GbiMe52MRnkSC4J7hm7/aL2EsQP5gsP3zSNS', 'PM' ),
(5,'pm2', 'pm2@mail.ru', 'pm2', 'pmov2','$2y$12$xSA2vSxfw8JONfp/0GbiMe52MRnkSC4J7hm7/aL2EsQP5gsP3zSNS', 'PM' ),
(6,'pm3', 'pm3@mail.ru', 'pm2', 'pmov2','$2y$12$xSA2vSxfw8JONfp/0GbiMe52MRnkSC4J7hm7/aL2EsQP5gsP3zSNS', 'PM' ),
(7,'pm4', 'pm4@mail.ru', 'pm2', 'pmov2','$2y$12$xSA2vSxfw8JONfp/0GbiMe52MRnkSC4J7hm7/aL2EsQP5gsP3zSNS', 'DEV' );



INSERT INTO project(id, name, code, user_id)
VALUES
(1,'111', 'admin@mail.ru',3  ),
(3,'222', 'pm@mail.ru', 3 ),
(5,'333', 'pm2@mail.ru', 7 ),
(6,'444', 'pm3@mail.ru',7 ),
(7,'55', 'pm5@mail.ru',7 );

insert into task(id,description, progress, status, deadline, user_id, project_id)
values
(1,'adsf',33,null,null,7,3),
(2,'adsf1',44,null,null,7,3),
(3,'adsf2',55,null,null,3,3);
