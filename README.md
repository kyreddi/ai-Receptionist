# AI Receptionist

Monorepo for the AI Receptionist SaaS MVP (India-first, extensible to US). It includes a Spring Boot backend and a Next.js admin dashboard.

## Structure

```
/backend   Spring Boot 3.x (Java 21)
/frontend  Next.js 14 App Router
/infra     docker-compose for local dev
```

## Quick Start (Docker)

```bash
cd infra
docker-compose up --build
```

- Backend: http://localhost:8080
- Swagger UI: http://localhost:8080/swagger-ui/index.html
- Frontend: http://localhost:3000

## Backend Notes

- Postgres is required for production. The compose file provisions a local instance.
- Flyway migrations are in `backend/src/main/resources/db/migration`.
- A demo tenant is seeded with ID `11111111-1111-1111-1111-111111111111`.

## API Overview

- `POST /auth/signup`
- `POST /auth/login`
- `GET /me`
- `GET/POST/PUT/DELETE /faqs`
- `GET/PUT /business-profile`
- `GET/POST /appointments`
- `GET /calls` and `GET /calls/{id}`
- `POST /webhooks/telephony/inbound`
- `POST /webhooks/telephony/status`
- `POST /simulator/chat`

## Environment Variables

Backend:
- `APP_JWT_SECRET` (required in production)

Frontend:
- `NEXT_PUBLIC_API_BASE` (defaults to `http://localhost:8080`)

## Tests

```bash
cd backend
./gradlew test
```
