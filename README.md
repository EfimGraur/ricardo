# Project Management Tool

PM Tool exposes a family of REST api's that facilitate the work of a project manager.

* Provides Rest endpoints that are secured using JWT
* Supports role-based access control (ADMIN, PM, DEV)
* The intent of this backend is to be consumed by a front end app
* Uses RDS to store data

---

* [Launch](#launch)
* [Description](#description)


---

## Launch

Recommended approach is to use docker-compose to launch backend + database;
```
docker-compose up
```
db/schema.sql contains table creation scrips that will be executed at container startup.

db/afterMigrate__seeds.sql pre-populates the DB with some data.

 
## Description

This backend is based on spring security that secures endpoints with JWT and grants access on a per role basis.
MariaDB RDS was used but any other system can be used.


