package seminars.seminar_5;

/**
 * {
 *     "connected": true
 * }
 */
public class LoginResponse {

    // причину
    private boolean connected;

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }
}
