package be.pxl.emailservice.sendgrid.events;

import be.pxl.emailservice.core.api.util.EqualsByStateObject;

@SuppressWarnings("squid:S2160")
public class BounceDto extends EqualsByStateObject {

    private String email;
    private String event;

    private BounceDto() {
    }

    public String getEmail() {
        return email;
    }

    public String getEvent() {
        return event;
    }

}
