package be.pxl.emailservice.sendgrid;

import static org.slf4j.LoggerFactory.getLogger;

import be.pxl.emailservice.config.ApplicationProperties;
import be.pxl.emailservice.core.api.EmailDto;
import be.pxl.emailservice.core.api.HiddenPixel;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import java.io.IOException;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class SendGridEmailRequestMapper {

    private static final Logger LOGGER = getLogger(SendGridEmailRequestMapper.class);

    private final ApplicationProperties properties;

    public SendGridEmailRequestMapper(ApplicationProperties properties) {
        this.properties = properties;
    }

    public Request mapToRequest(EmailDto dto) {
        var van = new Email(properties.getVerifiedSendGridEmailaddress(), dto.getAfzender());
        var naar = new Email(dto.getGeadresseerdeEmail(), dto.getGeadresseerdeNaam());
        var inhoud = new Content("text/html", dto.getInhoud() + HiddenPixel.from(dto.getCorrelatieUuid(), properties.getApplicationUrl()));
        var mail = new Mail(van, dto.getOnderwerp(), naar, inhoud);
        var request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        try {
            request.setBody(mail.build());
        } catch (IOException e) {
            LOGGER.info("mappen van EmailDto naar SendGrid Request object mislukt.");
        }
        return request;
    }
}
