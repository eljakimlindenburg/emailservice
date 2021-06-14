package be.pxl.emailservice.sendgrid;

import static org.slf4j.LoggerFactory.getLogger;

import be.pxl.emailservice.core.api.EmailDto;
import be.pxl.emailservice.core.api.Status;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import java.io.IOException;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class SendGridGateway {

    private static final Logger LOGGER = getLogger(SendGridGateway.class);

    private final SendGrid sendGrid;

    public SendGridGateway(SendGrid sendGrid) {
        this.sendGrid = sendGrid;
    }

    public EmailDto sendEmail(EmailDto dto, Request request) {
        Response response;
        try {
            response = sendGrid.api(request);
            if (isStatusCode2xx(response)) {
                LOGGER.info("Email met succes verzonden via SendGrid");
                dto.setStatus(Status.VERZONDEN);
            } else {
                LOGGER.info("Response code van SendGrid was niet 2xx");
                dto.setStatus(Status.VERZENDING_MISLUKT);
            }
        } catch (IOException e) {
            LOGGER.info("Email uitsturen via SendGrid is gefaald om een onbekende reden.");
            dto.setStatus(Status.VERZENDING_MISLUKT);
        }
        return dto;
    }

    private boolean isStatusCode2xx(Response response) {
        return (response.getStatusCode() / 100) == 2;
    }
}
