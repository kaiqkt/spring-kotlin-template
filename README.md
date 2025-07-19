# Kotlin Spring Boot Template

A template for Kotlin/Spring Boot applications with pre-configured best practices and common utilities.

## Prerequisites

- Java 17 or later
- Gradle 8.0 or later
- Docker (for containerization)

## Getting Started

### Running Locally

1. Clone the repository
2. Build the project:
   ```bash
   ./gradlew build
   ```
3. Run the application:
   ```bash
   ./gradlew bootRun
   ```

The application will be available at `http://localhost:8080` by default.

## Building a Docker Image

This project uses Spring Boot's buildpack support to create optimized Docker images. To build a Docker image:

```bash
# Build the application and create a Docker image
./gradlew bootBuildImage --imageName=your-service:latest

# Run the container
docker run -p 8080:8080 your-service:latest
```

## Configuration

Application properties can be configured in `src/main/resources/application.yml` or via environment variables.

## Testing

Run the tests with:

```bash
./gradlew test
```

## Contributing

1. Create a feature branch
2. Commit your changes
3. Push to the branch
4. Create a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.
