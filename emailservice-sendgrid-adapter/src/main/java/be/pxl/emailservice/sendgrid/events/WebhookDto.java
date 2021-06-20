package be.pxl.emailservice.sendgrid.events;

import be.pxl.emailservice.core.api.util.EqualsByStateObject;

@SuppressWarnings("squid:S2160")
public class WebhookDto extends EqualsByStateObject {

    private String email;
    private String event;

    private WebhookDto() {
    }

    public String getEmail() {
        return email;
    }

    public String getEvent() {
        return event;
    }

}
