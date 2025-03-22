package io.github.vishalmysore;



public class MessagePackage {
    private String message;
    private long timestamp;
    private String sender;

    // Constructors
    public MessagePackage() {
    }

    public MessagePackage(String message, long timestamp, String sender) {
        this.message = message;
        this.timestamp = timestamp;
        this.sender = sender;
    }

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
