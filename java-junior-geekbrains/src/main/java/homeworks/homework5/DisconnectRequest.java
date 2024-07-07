package homeworks.homework5;

import seminars.seminar_5.AbstractRequest;

public class DisconnectRequest  extends AbstractRequest {

    public static final String TYPE = "disconnect";

    public DisconnectRequest() {
        setType(TYPE);
    }
}
