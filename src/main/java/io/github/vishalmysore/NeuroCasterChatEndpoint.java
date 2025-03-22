package io.github.vishalmysore;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.t4a.predict.Tools4AI;
import com.t4a.processor.OpenAiActionProcessor;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

@Slf4j
@ServerEndpoint("/chat")
public class NeuroCasterChatEndpoint extends AbstractWebSocketHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("New connection: " + session.getId());
    }

    @OnMessage
    public String onMessage(String message, Session session) {
        System.out.println("Received message: " + message);

        // Process the input JSON message
        try {
            // Parse the incoming JSON message
            MessageInput input = objectMapper.readValue(message, MessageInput.class);

            String responseMessage = null;
            String methodName = null;
            String paramString = null;
            OpenAiActionProcessor processor = new OpenAiActionProcessor();
            if("query".equals(input.getAction())) {
                responseMessage = processor.query(input.getActualMessage());
            }
            else if("execute".equals(input.getAction())) {
                 responseMessage= Tools4AI.executeAction(input.getMethod(), input.getData()).toString();

            } else if("tools".equals(input.getAction())) {


                responseMessage = "I will execute the action";
                ChatHumanInLoop chatHumanInLoop = new ChatHumanInLoop();
                ExplainDecision explainDecision = new ExplainDecision();
                processor.processSingleAction(input.getActualMessage(), chatHumanInLoop, explainDecision);
                methodName = chatHumanInLoop.getMethodName();
                paramString = chatHumanInLoop.getParamsString();
            }

            // Create a JSON response
            String jsonResponse = String.format(
                    "{\"permission\": \"%s\", \"message\": \"%s\", \"methodName\": \"%s\", \"jsonStr\": %s}",
                    input.getPermission(),
                    responseMessage,
                    methodName,
                    paramString
            );

            log.info(jsonResponse);
            return jsonResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"status\": \"error\", \"message\": \"Failed to process the request\"}";
        }
    }

    private String processMessage(MessageInput input) {
        // Example logic: Return a response based on permission
        if (input.getPermission().equalsIgnoreCase("ask")) {
            return "Message received: " + input.getActualMessage();
        } else {
            return "Permission denied for message: " + input.getActualMessage();
        }
    }
    @OnClose
    public void onClose(Session session) {
        System.out.println("Connection closed: " + session.getId());
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println("Error in connection: " + throwable.getMessage());
    }
}
