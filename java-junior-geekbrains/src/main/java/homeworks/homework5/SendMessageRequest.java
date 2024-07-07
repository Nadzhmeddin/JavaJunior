package homeworks.homework5;


import seminars.seminar_5.AbstractRequest;

/**
 * {
 *     "type": "sendMessage",
 *     "recipient": "nagibator",
 *     "message": "text to nagibator"
 * }
 */
public class SendMessageRequest extends AbstractRequest {

    public static final String TYPE = "sendMessage";

    private String recipient;
    private String message;

    public SendMessageRequest() {
        this.setType(TYPE);
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return recipient;
    }
}
