package seminars.seminar_5;

/**
 * {
 *     "type": "broadcastMessage",
 *     "username": "nagibator",
 *     "message": "message to all users"
 * }
 */

public class BroadcastMessageRequest extends AbstractRequest {

    public static final String TYPE = "broadcastMessage";

    private String userName;
    private String message;


    public BroadcastMessageRequest() {
        this.setType(TYPE);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
