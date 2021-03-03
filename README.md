# Task Scheduler

## How to Run

### Step 1: Build the jar file

`./mvnw clean package`

### Step 2: Run the app using docker-compose

`docker-compose up --build`

## RESTful Endpoints

> Note: All endpoints are preceded by /api/v1

## `GET /tasks`

Gets all tasks in order by due date

### Param Options:

#### desc: tasks are reversed if set to 'true'

#### sortBy: tasks are sorted by priority from HIGH to LOW if set to 'priority'

## `GET /tasks/:id`

Gets a specific task based on its id

## `POST /tasks`

Creates a new task

#### Request Body:

`dueDate: LocalDateTime format is MM-dd-yyyy HH:mm:ss`

`title: String`

`description: String`

`priority: either "LOW", "MEDIUM", or "HIGH"`

`status: either "QUEUED" or "RESOLVED"`

## `PATCH /tasks/:id`

Updates a specific task by id

#### Request Body:

`one or more fields from POST /tasks`

## `DELETE /tasks/:id`

Deletes a specific task by id
