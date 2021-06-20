package be.pxl.emailservice.sendgrid;

import static org.slf4j.LoggerFactory.getLogger;

import be.pxl.emailservice.core.api.service.EmailService;
import be.pxl.emailservice.sendgrid.events.WebhookDto;
import java.util.List;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/sendgrid")
public class SendGridWebhookController {

    private static final Logger LOGGER = getLogger(SendGridWebhookController.class);
    private static final String BOUNCE = "bounce";

    private final EmailService emailService;

    public SendGridWebhookController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping(path = "/bounce")
    public void processBounce(@RequestBody List<WebhookDto> dtos) {
        LOGGER.info("webhook events ontvangen: {}", dtos);
        dtos.forEach(d -> {
            if (d.getEvent().equalsIgnoreCase(BOUNCE)) {
                emailService.verwerkBounce(d.getEmail());
            }
        });
    }
}
