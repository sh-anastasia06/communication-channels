### Communication Channels (Spring Boot + JDBC) ###

## Architecture
The project strictly adheres to the principle of Layered Architecture using interfaces:
`Controller ? Service Interface ? Service Implementation ? Repository Interface ? Repository Implementation`.
All the logic is described in the Service Implementation.

## Data models (DTO / Records)
Java Records are used for data transfer.
Immutability: The data inside the DTO cannot be changed.

## Data layer (Repository Implementation)

The implementation is based on the NamedParameterJdbcTemplate.

## API Endpoints

| Method | Endpoint | Query Parameters | Description |
| :--- | :--- | :--- | :--- |
| `GET` | `/api/alltypest` | `filter` *(optional)* | Retrieves a list of types. Supports optional filtering by search query. |
| `POST` | `/api/newtypest` | None | Creates a new type entry. |
| `PUT` | `/api/updatetypest` | None | Updates an existing type entry. |
