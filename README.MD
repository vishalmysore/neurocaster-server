# Model Integration Protocol : Neurocaster

This project is a Model Integration Protocol (MIP) implementation, similar to the Model Context Protocol (MCP). It dynamically triggers actions or tools, which can be written in Java, HTTP REST calls, or shell scripts. All existing calls can be converted to tools.

## Project Structure

- `src/main/java`: Contains Java source files for the tools and other project components.
- `src/main/resources`: Contains configuration files and other resources.
    - `tools4ai.properties`: Configuration for AI tools.
    - `application.properties`: Spring Boot application configuration.
    - `formated.txt`: JSON schema definitions for tools.
    - `serverinfo.txt`: Server information and logs.

## Configuration

### `tools4ai.properties`

This file contains configuration for AI tools:

```ini
modelName=gemini-1.0-pro
location=us-central1
projectId=cookgptserver
openAiKey=demo
```

### `application.properties`

This file contains Spring Boot application configuration:

```ini
spring.ai.openai.api-key=demo
server.tomcat.connection-timeout=
server.tomcat.keep-alive-timeout=-1
management.endpoints.web.exposure.include=*
management.endpoint.health.show-details=always
management.server.port=8081
management.endpoints.web.cors.allowed-origins=http://localhost:4200
management.endpoints.web.cors.allowed-methods=GET
```

## Tools

Tools are defined in the `formated.txt` file using JSON schema. Each tool has a name, description, and input schema. Here are some examples:

- **Echo**: Echoes back the input message.
- **Add**: Adds two numbers.
- **PrintEnv**: Prints all environment variables.
- **LongRunningOperation**: Demonstrates a long-running operation with progress updates.
- **SampleLLM**: Samples from an LLM using MCP's sampling feature.
- **GetTinyImage**: Returns the MCP_TINY_IMAGE.
- **AnnotatedMessage**: Demonstrates how annotations can be used to provide metadata about content.

## Usage

To use the tools, you can trigger them dynamically through the MIP. The tools can be implemented in Java, HTTP REST calls, or shell scripts. All existing calls can be converted to tools.

## Running the Project

1. Clone the repository.
2. Navigate to the project directory.
3. Build the project using Maven:
   ```sh
   mvn clean install
   ```
4. Run the Spring Boot application:
   ```sh
   mvn spring-boot:run
   ```

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.