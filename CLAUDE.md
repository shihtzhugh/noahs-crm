# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Noah's CRM is a comprehensive small-business management platform designed to provide a centralized source for all aspects of running a small business. The application aims to replace fragmented tools and spreadsheets with an integrated system covering job management, client relationships, team coordination, asset tracking, financial oversight, and document storage.

**Current Implementation Status:** The Assets module (specifically Tool and Vehicle tracking) is the first module being developed. The full MVP will include seven core modules as outlined below.

### Technology Stack
- **Backend**: Spring Boot 3.5.6 with Java, JPA/Hibernate, PostgreSQL
- **Frontend**: SvelteKit with TypeScript and Vite
- **Build Tools**: Gradle (multi-module), npm
- **Code Quality**: SonarQube integration

### Repository Structure
```
noahs-crm/
├── backend/           # Spring Boot application
│   ├── app/          # Main application module
│   │   └── src/
│   │       ├── main/java/com/shihtzhugh/  # Application code
│   │       └── test/java/                 # Tests
│   ├── build.gradle.kts
│   ├── settings.gradle.kts
│   └── gradlew       # Gradle wrapper
└── frontend/         # SvelteKit application
    ├── src/
    │   ├── routes/   # SvelteKit file-based routing
    │   └── lib/      # Shared components and utilities
    ├── package.json
    └── vite.config.ts
```

## MVP Module Architecture

The application is designed around seven core modules:

### 1. Dashboard 📊
**Landing page after login**

Shows key business metrics at a glance: upcoming appointments/deadlines, recent activity, cash flow summary, active jobs status, and alerts (expiring certifications, low inventory, overdue invoices). Provides immediate situational awareness without navigating multiple screens.

**Value:** Saves time by surfacing critical information; helps owner make quick decisions.

### 2. Jobs 🔨
**Project/contract lifecycle management**

Dropdown sub-modules:
- **Active Jobs**: In-progress work with status tracking
- **Pipeline**: Potential jobs and quotes/estimates
- **Completed**: Historical record with profitability analysis

Tracks the entire sales-to-completion cycle. Each job links to the client, assigned employees, materials/assets used, and financials. Can generate estimates, convert to active jobs, track time and expenses, and mark milestones.

**Value:** Central source of truth for revenue-generating activities; ensures nothing falls through the cracks.

### 3. Clients 👥
**Customer relationship management**

Maintains client database with contact info, communication history, job history, outstanding invoices, and notes. Quick access to "who owes what" and "what did we last discuss?"

**Value:** Builds institutional knowledge; prevents loss of context when employees transition; enables better customer service.

### 4. Team 👷
**Employee management**

Dropdown sub-modules:
- **Directory**: Employee contact info, roles, certifications
- **Time & Attendance**: Clock in/out, timesheet approval
- **Payroll**: (Could integrate with existing payroll service or track hours for manual processing)

Centralizes employee data, tracks who's available/qualified for jobs, monitors labor costs per project.

**Value:** Ensures compliance (certifications current), optimizes scheduling, connects labor costs to job profitability.

### 5. Assets 🚗
**Equipment and inventory tracking** *(Currently in development)*

Tracks tools, vehicles, IT equipment, materials inventory. Records maintenance schedules, current location/assignment, purchase date, depreciation. Can flag items needing service or replacement.

**Value:** Prevents loss/theft through accountability; reduces downtime via preventive maintenance; supports accurate job costing.

### 6. Finances 💰
**Money in/out tracking**

Dropdown sub-modules:
- **Invoices**: Generate, send, track payment status
- **Expenses**: Record costs (can link to jobs or general overhead)
- **Reports**: P&L, cash flow, job profitability

If using QuickBooks API, this becomes a dashboard/interface layer. If rolling your own, handles basic accounting functions.

**Value:** Answers "are we making money?" and "where is our money going?"; supports tax prep and growth planning.

### 7. Documents 📄
**Centralized file storage**

Stores contracts, insurance certificates, permits, photos (before/after job site), employee files, vendor agreements. Organized by category with search functionality.

**Value:** Eliminates "where did we put that contract?" panic; supports compliance and dispute resolution.

### Key Design Principles
- **Cross-Module Linking**: Jobs link to clients, employees, assets, and financials
- **Institutional Knowledge**: Preserve context and history (e.g., client communication logs)
- **Accountability**: Track who has what (assets), who did what (time), who owes what (invoices)
- **Compliance**: Monitor certifications, permits, and maintenance schedules
- **Business Intelligence**: Connect labor costs to job profitability, track cash flow

## Backend Architecture

### Current Structure
```
backend/app/src/main/java/com/shihtzhugh/
├── Application.java           # Main Spring Boot application entry point
├── controller/                # REST API endpoints (e.g., ToolController)
├── service/                   # Business logic layer (e.g., ToolService)
├── repository/                # JPA repositories (e.g., ToolRepository)
├── model/                     # JPA entities (Tool, Vehicle)
└── model/dto/                 # Data Transfer Objects (ToolDto, VehicleDto)
```

### Established Patterns
- **Entity-DTO Conversion**: Entities have static `from(Dto)` factory methods, DTOs have static `from(Entity)` factory methods
- **Repository Pattern**: All repositories extend `JpaRepository<Entity, Long>`
- **Service Layer**: Services are annotated with `@Service` and `@Transactional`
- **REST API Convention**: Controllers use `@RestController` with `/api/v1/{resource}` base path
- **Constructor Injection**: Services and controllers use constructor-based dependency injection

### Database Configuration
- PostgreSQL on `localhost:5434`
- Hibernate DDL mode: `create-drop` (recreates schema on startup - development only)
- SQL logging enabled for debugging
- Configuration in `backend/app/src/main/resources/application.yml`

**Note**: For production, change `ddl-auto` to `validate` or `none` and use migration tools (Flyway/Liquibase).

## Frontend Architecture

### Current Structure
- **Framework**: SvelteKit with file-based routing
- **Styling**: TailwindCSS (assumed based on common SvelteKit patterns)
- **Build Tool**: Vite for fast HMR and optimized builds
- **Type Safety**: TypeScript throughout

### Key Patterns
- Components in `src/lib/` for reusability
- Routes in `src/routes/` following SvelteKit conventions
- API calls to backend at `/api/v1/*` endpoints

## Common Commands

### Backend Development

Navigate to backend directory: `cd backend`

**Build the project:**
```bash
./gradlew build
```

**Run tests:**
```bash
./gradlew test
```

**Run a specific test class:**
```bash
./gradlew test --tests com.shihtzhugh.service.ToolServiceTest
```

**Start the Spring Boot application:**
```bash
./gradlew bootRun
```

**Run all checks (tests + SonarQube):**
```bash
./gradlew check
```

**Build executable JAR:**
```bash
./gradlew bootJar
```

**Build Docker image:**
```bash
./gradlew bootBuildImage
```

**Clean build artifacts:**
```bash
./gradlew clean
```

### Frontend Development

Navigate to frontend directory: `cd frontend`

**Install dependencies:**
```bash
npm install
```

**Start development server:**
```bash
npm run dev
```

**Build for production:**
```bash
npm run build
```

**Preview production build:**
```bash
npm run preview
```

**Type-check:**
```bash
npm run check
```

**Type-check with watch mode:**
```bash
npm run check:watch
```

## Development Guidelines

### Adding New Modules/Entities

When implementing a new domain entity (e.g., Job, Client, Employee):

1. **Create JPA Entity** in `backend/app/src/main/java/com/shihtzhugh/model/` package
   - Use appropriate relationships (`@ManyToOne`, `@OneToMany`, etc.) to link related modules
   - Include a protected no-args constructor for JPA
   - Add a static `from(Dto)` factory method

2. **Create DTO** in `model/dto/` package
   - Use immutable fields (final)
   - Add a static `from(Entity)` factory method

3. **Create Repository** extending `JpaRepository<Entity, Long>` in `repository/` package
   - Add custom query methods as needed

4. **Create Service** in `service/` package
   - Annotate with `@Service` and `@Transactional`
   - Use constructor injection for dependencies
   - Handle business logic and entity-DTO conversions

5. **Create REST Controller** in `controller/` package
   - Annotate with `@RestController`
   - Use `@RequestMapping("/api/v1/{resource-name}")`
   - Return `ResponseEntity<T>` for proper HTTP status handling

6. **Create Frontend Components** (as needed)
   - Add form components in `frontend/src/lib/`
   - Create routes in `frontend/src/routes/`
   - Implement API calls to backend endpoints

### Cross-Module Relationships

When implementing relationships between modules (e.g., Job → Client, Job → Assets):
- Use JPA relationships on entity level
- Consider the business value: Jobs should know their client, but does a Client need to eagerly load all jobs?
- Use appropriate fetch strategies to avoid N+1 queries
- Design APIs to support linking (e.g., POST /api/v1/jobs with clientId in request body)

### API Design

- **Collections**: `GET /api/v1/resources` → returns `List<ResourceDto>`
- **Single Resource**: `GET /api/v1/resources/{id}` → returns `ResourceDto`
- **Create**: `POST /api/v1/resources` with body → returns created `ResourceDto`
- **Update**: `PUT /api/v1/resources/{id}` with body → returns updated `ResourceDto`
- **Delete**: `DELETE /api/v1/resources/{id}` → returns 204 No Content

## Git Workflow

- **Main Branch**: `master` (use for pull requests)
- **Development Branch**: `develop` (current active development)

## Testing

Tests use JUnit 5 (Jupiter) with Spring Boot Test support.

**Location**: `backend/app/src/test/java/` (mirrors main structure)

**Run all tests:**
```bash
cd backend
./gradlew test
```

**Run with detailed output:**
```bash
./gradlew test --info
```

## Future Considerations

### Additional Features (Post-MVP)
- **Settings/Admin**: Company profile, user management, module configurations, integrations (QuickBooks, email), backup/export
- **Messages/Email Integration**: Could be integrated into relevant modules (e.g., message client from their profile page, email employee from Team module) rather than standalone, to keep MVP lean

### Technical Enhancements
- **Authentication/Authorization**: Multi-user support with role-based access (owner, employee, accountant)
- **External Integrations**: QuickBooks API for finances, email integration
- **File Uploads**: Document storage for the Documents module
- **Notifications**: Alerts for expiring certifications, overdue invoices, low inventory
- **Mobile Access**: Responsive design for field employees clocking in/out or updating job status
