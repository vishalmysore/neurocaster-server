# 🚀 **Model Integration Protocol (MIP) with Spring Boot**

This project demonstrates how to **integrate Spring Boot methods into LLMs** using **Model Integration Protocol (MIP)** by exposing them as **JSON-RPC tools**.  
With `@Agent` and `@Action` annotations, any existing Spring Boot service becomes an **AI-triggerable tool**.

---

## 🛠️ **Features**
- 🔥 **Spring Boot + MIP Integration:** Convert existing methods into JSON-RPC tools.
- ⚙️ **Annotations:**
   - `@Agent`: Declares a service as an AI-invocable agent.
   - `@Action`: Exposes a method as an actionable tool.
   - `@Prompt`: Defines custom prompts, like date formats, for better LLM interpretation.
- ⚡ **Tools4AI Execution:** LLMs use **Tools4AI** to trigger the exposed services dynamically.
- 🗨️ **WebSocket Integration:** All Spring services annotated with `@Agent` and `@Action` can now be used for chat interactions with LLMs.

---

## ⚙️ WebSocket Configuration
To integrate your Spring Boot application for chat interactions, configure WebSocket and the dependency as follows:

🧩 WebSocket Configuration
Add the following configuration class to enable WebSocket support:


```  
@Configuration
@EnableWebSocket
public class NeuroCasterWebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler(), "/chat")
                .addInterceptors(new HttpSessionHandshakeInterceptor())
                .setAllowedOrigins("*");  // Allow all origins or specific ones
    }

    @Bean
    public WebSocketHandler webSocketHandler() {
        return new NeuroCasterChatEndpoint(); // Use the ChatEndpoint class as WebSocketHandler
    }

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}

```
📦 Add Dependency
Add the following dependency to your pom.xml:

```  
<dependency>
    <groupId>io.github.vishalmysore</groupId>
    <artifactId>neurocaster-server</artifactId>
    <version>0.0.1</version>
</dependency>

```

## 🔥 **Example Services**

### 🛠️ **Customer Support Service**
```java
@Service
@Agent(groupName = "customer support", groupDescription = "Actions related to customer support")
public class ComplexActionService {

    public static int COUNTER = 0;

    public ComplexActionService() {
        COUNTER++;
    }

    @Action(description = "Customer has a problem, create a ticket for them")
    public String computerRepair(Customer customer) {
        return customer.toString();
    }
}
```

```
{
    "methodName": "computerRepair",
    "parameters": [
        {
            "name": "customer",
            "type": "Customer",
            "fieldValue": {
                "name": "Alice",
                "email": "alice@example.com",
                "issue": "Laptop not booting"
            }
        }
    ],
    "returnType": "String"
}


```
### ⚙️ Tools4AI Integration
To integrate with LLMs using Tools4AI, the services are exposed as JSON-RPC tools.
When the LLM receives a query, it:

Selects the most relevant tool based on the context.

Generates the JSON-RPC request.

Invokes the Spring Boot service dynamically.

Returns the result back to the LLM.

### 🔥 Customization
You can:

Add more services with @Agent and @Action annotations.

Use @Prompt to specify custom prompts for date formatting or other field interpretation.

Scale by adding additional JSON-RPC tools, making your Spring Boot app LLM-compatible.

### 🚀 Contributing
Fork the repo and submit a pull request.

Add more services, improve error handling, or optimize the JSON-RPC layer.