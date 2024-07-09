# IntuitCraftDemoBackend

## Overview

This project is a backend implementation for IntuitCraftDemo, focusing on providing efficient and scalable solutions.

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [Configuration](#configuration)
- [API Endpoints](#api-endpoints)
- [Contributing](#contributing)
- [License](#license)

## Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/keshav21/IntuitCraftDemoBackend.git
    ```
2. Navigate to the project directory:
    ```bash
    cd IntuitCraftDemoBackend
    ```
3. Build the project using Maven:
    ```bash
    ./mvnw clean install
    ```

## Usage

1. To run the application:
    ```bash
    ./mvnw spring-boot:run
    ```
2. Access the application at `http://localhost:8080`.

## Configuration

### Docker

To deploy using Docker:
1. Build the Docker image:
    ```bash
    docker build -t intuit-craft-demo-backend .
    ```
2. Run the Docker container:
    ```bash
    docker run -p 8080:8080 intuit-craft-demo-backend
    ```

### Kubernetes

To deploy using Kubernetes:
1. Apply the deployment configuration:
    ```bash
    kubectl apply -f app-deployment.yaml
    ```

## API Endpoints

### User Management

- **Get All Users**
  - **Method**: GET
  - **URL**: `/api/users`
  - **Description**: Retrieves a list of all users.
  
- **Get User by ID**
  - **Method**: GET
  - **URL**: `/api/users/{id}`
  - **Description**: Retrieves a specific user by ID.
  - **Parameters**: 
    - `id` (path parameter): ID of the user to retrieve.
  
- **Create User**
  - **Method**: POST
  - **URL**: `/api/users`
  - **Description**: Creates a new user.
  - **Body**:
    ```json
    {
      "name": "string",
      "email": "string",
      "password": "string"
    }
    ```

- **Update User**
  - **Method**: PUT
  - **URL**: `/api/users/{id}`
  - **Description**: Updates an existing user by ID.
  - **Parameters**:
    - `id` (path parameter): ID of the user to update.
  - **Body**:
    ```json
    {
      "name": "string",
      "email": "string",
      "password": "string"
    }
    ```

- **Delete User**
  - **Method**: DELETE
  - **URL**: `/api/users/{id}`
  - **Description**: Deletes a user by ID.
  - **Parameters**:
    - `id` (path parameter): ID of the user to delete.

### Photographers Management

- **Get All Photographers**
  - **Method**: GET
  - **URL**: `/api/photographers`
  - **Description**: Retrieves a list of all photographers.
  
- **Get Photographer by ID**
  - **Method**: GET
  - **URL**: `/api/photographers/{id}`
  - **Description**: Retrieves a specific photographer by ID.
  - **Parameters**:
    - `id` (path parameter): ID of the photographer to retrieve.
  
- **Get Photographer by a Event**
  - **Method**: POST
  - **URL**: `/api/photographers/event/{eventType}`
  - **Description**: Retrieves a specific photographers by eventType.
  - **Parameters**:
    - `eventType` (path parameter): eventType of the photographer to retrieve. 

## Contributing

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Commit your changes (`git commit -am 'Add new feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Create a new Pull Request.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
