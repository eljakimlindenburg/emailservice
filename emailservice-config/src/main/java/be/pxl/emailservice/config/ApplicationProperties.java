package be.pxl.emailservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class ApplicationProperties {

    @Value("${email.sendgrid.apiKey}")
    private String sendGridApiKey;

    @Value("${email.sendgrid.verified.emailaddress.from}")
    private String verifiedSendGridEmailaddress;

    @Value("${application.url}")
    private String applicationUrl;

    public String getApplicationUrl() {
        return applicationUrl;
    }

    public String getSendGridApiKey() {
        return sendGridApiKey;
    }

    public String getVerifiedSendGridEmailaddress() {
        return verifiedSendGridEmailaddress;
    }

}
