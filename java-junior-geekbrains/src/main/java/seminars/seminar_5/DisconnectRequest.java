package seminars.seminar_5;

public class DisconnectRequest  extends AbstractRequest{

    public static final String TYPE = "disconnect";

    public DisconnectRequest() {
        setType(TYPE);
    }
}
