package topaz.core.messages;

public class Message {

    private String message;
    private Object data;

    public Message(String message) {
        this.message = message;
    }

    public Message(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}