# Email-Service

A Spring Boot application showcasing an email service that allows sending both simple text and HTML formatted emails. This service provides REST APIs to send emails, integrates Swagger for easy API documentation, and includes a global exception handler for robust error management.

## Features

- **Sending Simple Mail**: Send plain text emails via a simple REST API.
- **Sending HTML Decorated Mail**: Send emails with HTML formatting to enhance presentation.
- **Sending Mai with Attachment**: Seamlessly send emails that include file attachments to provide additional content and context.
- **Sending Mail with Inline Image**: Embed images directly within the body of your emails for an enhanced visual experience.
- **Sending Template based Mail**: Send personalized emails using pre-defined templates with dynamic content integration.
- **Global Exception Handler**: Handles exceptions globally across all controllers, ensuring a consistent response and error handling mechanism.
- **Swagger Integration**: Provides a UI for easy testing and exploration of available API endpoints.
- **Highly Maintainable Code**: The project is structured with scalability and maintainability in mind, following best practices for Spring Boot development.

## Setup and Installation

### Prerequisites

Before running the application, ensure you have the following installed:

- **JDK 8 or above**: Required to run the Spring Boot application.
- **GRADLE**: For building and managing the project dependencies.

### Cloning the Repository

Clone this repository to your local machine using Git:

```bash
git clone https://github.com/kunalbandooni/Email-Service.git
```

### Build the Project

Navigate to the project directory and build the application using GRADLE:

```bash
cd email-service  
gradle build
```

### Running the Application

Once the project is built, you can run the application using:

```bash
gradle bootRun
```

The application will start running on `http://localhost:5000`.

## Swagger UI for API Exploration

- **Endpoint**: `http://localhost:5000/swagger-ui.html`
- Swagger UI provides a user-friendly interface to interact with the API and see all the available endpoints with sample requests and responses.

## Global Exception Handler

The application includes a **global exception handler** that ensures any unexpected errors or exceptions are properly managed and communicated in the response.

- **Exception Handler**: All exceptions are captured by a `@ControllerAdvice` class, which returns a consistent error response structure with relevant error details.

## Code Structure

- **Controller Layer**: Handles all REST API requests.
- **Service Layer**: Contains logic for sending emails in expected formats.
- **Exception Handler**: Global exception handler to catch all exceptions and return proper responses.
- **Swagger Configuration**: For generating API documentation and testing.

## Technology Stack

- **Spring Boot**: The core framework for building the application.
- **Spring Mail**: To send email messages.
- **Swagger**: For API documentation.
- **Gradle**: For dependency management and project build.

## Contribution

Feel free to fork this repository and make contributions. Any improvements, bug fixes, or new features are welcome! To contribute:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature/your-feature-name`).
3. Make your changes.
4. Commit your changes (`git commit -am 'Add some feature'`).
5. Push to the branch (`git push origin feature/your-feature-name`).
6. Open a pull request.
