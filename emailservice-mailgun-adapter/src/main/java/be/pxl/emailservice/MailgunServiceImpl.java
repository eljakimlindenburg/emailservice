package be.pxl.emailservice;

import static org.slf4j.LoggerFactory.getLogger;

import be.pxl.emailservice.config.ApplicationProperties;
import be.pxl.emailservice.core.api.EmailDto;
import be.pxl.emailservice.core.api.HiddenPixel;
import be.pxl.emailservice.core.api.Status;
import be.pxl.emailservice.core.api.service.MailgunService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class MailgunServiceImpl implements MailgunService {

    private static final Logger LOGGER = getLogger(MailgunServiceImpl.class);

    private final ApplicationProperties properties;

    public MailgunServiceImpl(ApplicationProperties properties) {
        this.properties = properties;
    }

    @Override
    public EmailDto verstuurEmail(EmailDto dto) {
        JsonNode result = null;
        try {
            result = sendSimpleMessage(dto);
            LOGGER.info("Email met succes verzonden via Mailgun: {}", result);
            dto.setStatus(Status.VERZONDEN);
        } catch (UnirestException e) {
            LOGGER.info("Verzending via Mailgun gefaald: {}", result);
            dto.setStatus(Status.VERZENDING_MISLUKT);
        }
        return dto;
    }

    private JsonNode sendSimpleMessage(EmailDto dto) throws UnirestException {
        HttpResponse<JsonNode> request = Unirest.post("https://api.mailgun.net/v3/sandbox8c5e4d9d95e448c9a66ad76f95e6c48d.mailgun.org/messages")
                .basicAuth("api", properties.getMailgunApiKey())
                .queryString("from", properties.getVerifiedSendGridEmailaddress())
                .queryString("to", properties.getVerifiedMailgunEmailaddress())
                .queryString("subject", dto.getOnderwerp())
                .queryString("html", String.format("%s %s", dto.getInhoud(), HiddenPixel.from(dto.getCorrelatieUuid(), properties.getApplicationUrl()).getPixel()))
                .asJson();
        return request.getBody();
    }
}
