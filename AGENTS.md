# AGENTS.md

## Repository intent

This repository is a student project for a Dropbox-like file storage application.

Expected architecture:
- Spring Boot backend
- Angular frontend
- file upload / download flows
- authentication and authorization
- metadata persistence
- external file storage integration

Review changes with focus on:
- correctness
- security
- clean code and maintainability
- learning good engineering practices

Do NOT ignore code quality. This project should teach good coding habits.

---

## Global review priorities

When reviewing pull requests, prioritize the following:

1. Security and secret handling
2. Correctness of logic and data flow
3. Code quality and maintainability (clean code)
4. Authentication and authorization correctness
5. File storage and upload/download safety
6. API contract and validation
7. Configuration and environment safety
8. Tests for changed behavior

---

## Clean code and maintainability rules

This is a learning project. Always enforce good coding practices.

Review for:

- meaningful and consistent naming (variables, methods, classes)
- small, focused methods (avoid large “god methods”)
- single responsibility principle
- clear separation of concerns (controller vs service vs persistence)
- avoiding duplication (DRY)
- readable control flow (avoid deeply nested logic)
- avoiding unnecessary complexity
- proper error handling
- consistent structure across similar components

Flag as issues:

- unclear or misleading names
- duplicated logic
- large classes or methods doing too many things
- mixing business logic with controller or infrastructure code
- tight coupling that makes testing or extension difficult
- “magic values” without explanation
- poor readability even if code technically works

Do NOT:
- force unnecessary abstractions
- introduce patterns that add complexity without real benefit
- suggest overengineering for simple features

Prefer simple, clean, and maintainable solutions.

---

## Critical security rules

Always flag these as high priority:

- plaintext passwords, secrets, API keys, tokens, or credentials committed to the repository
- hardcoded Spring Security credentials
- secrets in:
    - `application.properties`
    - `application.yml`
    - Angular environment files
    - docker compose
    - test data reused in unsafe ways
- missing authorization on protected endpoints
- IDOR risks (user accessing another user's files by ID/path manipulation)
- unrestricted file upload/download endpoints
- unsafe file path handling
- trusting client-provided file metadata without validation
- exposing internal system paths or sensitive data

Treat these as P0/P1 issues.

---

## Spring Boot backend review rules

Focus on:

### Architecture and code quality
- controllers should be thin (no business logic)
- services should contain business logic
- repositories should handle persistence only
- avoid leaking database logic into controllers
- ensure code is testable

### Security
- verify authentication and authorization
- check ownership of resources (files, metadata)
- ensure endpoints are properly protected
- flag insecure defaults

### Configuration
- review `application.properties` and profiles carefully
- flag local-only credentials committed to repo
- prefer environment variables or local profiles

### API design
- validate inputs (size, type, nulls)
- use proper HTTP status codes
- avoid breaking changes unless intentional
- ensure predictable API behavior

### Persistence and migrations
- review Liquibase changes carefully
- avoid destructive changes without explanation
- ensure consistency between metadata and storage

### File storage logic
- avoid orphaned files or metadata
- handle partial failures correctly
- validate file size/type
- ensure safe file naming and paths

---

## Angular/frontend review rules

Focus on:

- clean component structure
- separation of concerns (UI vs logic vs services)
- proper handling of authentication state
- avoiding duplication
- validation of user input (especially file uploads)
- clear and maintainable code

Flag:

- leaking secrets into frontend config
- insecure storage (e.g., sensitive data in localStorage)
- tightly coupled components
- poor error handling

---

## Docker / environment review rules

Review:

- docker compose configuration
- environment variables
- exposed ports
- credentials

Flag:

- hardcoded credentials
- unsafe defaults
- configs that should be local-only but are committed
- fragile startup dependencies

---

## Tests

Tests are important for learning good practices.

Prefer tests for:
- business logic
- security rules
- file handling
- API endpoints
- edge cases

Flag:

- missing tests for important logic
- tests that only cover happy path
- untestable code structure

---

## Severity rubric

### P0
- security vulnerability
- hardcoded secrets
- broken access control
- data loss or corruption risk

### P1
- incorrect logic
- missing critical validation
- missing tests for important behavior
- bad architecture decisions that will cause issues

### P2
- maintainability issues
- code smells
- weak structure or readability

### P3
- minor issues or cosmetic suggestions

---

## Preferred review style

When leaving comments:

- explain WHY something is a problem
- describe the impact (bug, security risk, maintainability issue)
- suggest a concrete fix
- prioritize clarity over verbosity

Balance:
- teaching good practices
- avoiding unnecessary complexity
- keeping code simple but well-structured
