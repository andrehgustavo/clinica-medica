# clinica-medica
> Medical Clinics management system.

[![Spring-Boot Version][spring-boot-image]][spring-boot-url]
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

This project is a RestFul application, in which it is possible to register, list, edit and delete doctors and specialties and list the doctors of a specific specialty.


## Requirements
For building and running the application you need:

- [JDK 1.8](https://www.oracle.com/java/technologies/javase-downloads.html#JDK11)
- [Maven 3](https://maven.apache.org/) (or later)
- [Postgres 13](https://www.postgresql.org/about/news/postgresql-13-released-2077/)


## Installation

1. Clone the repository

```sh
git clone https://github.com/andrehgustavo/clinica-medica.git
```

2. Configure PostgreSQL
First, create a database named medicalDb. Then, open src/main/resources/application.properties file and change the spring datasource username and password as per your PostgreSQL installation.

## Usage example

- As a user, I would like to list all doctors;
- As a user, I would like to register a new doctor;
- As a user, I would like to edit an existing doctor;
- As a user, I would like to add specialties to a doctor;
- As a user, I would like to filter doctors by specialty;
- As a user, I would like to list all specialties;
- As a user, I would like to register a new specialty;
- As a user, I would like to edit a new specialty;

## Development setup


There are several ways to run a Spring Boot application on your local machine. One way is to execute the main method in the br.com.projetos.clinicamedica.ClinicaMedicaApplication class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) plugin like:

```shell
cd clinica-medica/clinicamedica
mvn spring-boot:run
```

## Endpoints
    - [GET] List all doctors ("api/doctors")
    - [GET] Read a doctors ("api/doctors/{id}")
    - [POST] Add new doctors ("api/doctors/")
    - [PUT] Update a doctors ("api/doctors/")
    - [DELETE] Delete a doctors ("api/doctors/{id}")

    - [GET] List all specialties ("api/specialties")
    - [GET] Read a specialties ("api/specialties/{id}")
    - [POST] Add new specialties ("api/specialties/")
    - [PUT] Update a specialties ("api/specialties/")
    - [DELETE] Delete a specialties ("api/specialties/{id}")



## Meta

André Gustavo Barros – [@andrehgustavo](https://www.linkedin.com/in/andr%C3%A9-gustavo-barros-457b9a43/) – andreh_gustavo@hotmail.com

[clinica-medica](https://github.com/andrehgustavo/clinica-medica)

<!-- Markdown link & img dfn's -->
[spring-boot-image]: https://spring.io/images/projects/spring-boot-7f2e24fb962501672cc91ccd285ed2ba.svg
[spring-boot-url]: https://ci.spring.io/teams/spring-boot/pipelines/spring-boot-2.4.x


