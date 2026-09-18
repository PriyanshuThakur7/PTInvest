# PTInvest Contributor Guide

## Project layout

- `backend/PTInvest` contains the Spring Boot application.
- Application source is under `backend/PTInvest/src/main/java/com/priyanshu/PTInvest`.
- Tests are under `backend/PTInvest/src/test/java`.
- Runtime configuration is in `backend/PTInvest/src/main/resources/application.yaml`.

## Development

Use Java 21. From `backend/PTInvest`, run:

```powershell
.\mvnw.cmd test
.\mvnw.cmd spring-boot:run
```

Use PostgreSQL for local development. Keep credentials and other environment-specific secrets out of committed source files; prefer environment variables or a local, ignored configuration override.

## Code conventions

- Keep packages under `com.priyanshu.PTInvest`.
- Follow standard Spring Boot layering: controllers for HTTP concerns, services for business logic, and repositories for persistence.
- Prefer constructor injection over field injection.
- Add or update focused tests with behavior changes.
- Avoid unrelated refactors and generated build output in commits.

## Before handing off changes

Run the relevant Maven tests and report any checks that could not be run, including missing local services such as PostgreSQL.
