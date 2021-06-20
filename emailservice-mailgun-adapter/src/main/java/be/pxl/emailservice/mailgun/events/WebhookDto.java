package be.pxl.emailservice.mailgun.events;

import be.pxl.emailservice.core.api.util.EqualsByStateObject;

@SuppressWarnings("squid:S2160")
public class WebhookDto extends EqualsByStateObject {

    private String event;
    private String recipient;

    private WebhookDto() {
    }

    public String getEvent() {
        return event;
    }

    public String getRecipient() {
        return recipient;
    }
}
