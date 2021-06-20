package be.pxl.emailservice;

import static org.slf4j.LoggerFactory.getLogger;

import be.pxl.emailservice.core.api.service.EmailService;
import be.pxl.emailservice.events.WebhookDto;
import java.util.List;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/mailgun")
public class MailgunWebhookController {

    private static final Logger LOGGER = getLogger(MailgunWebhookController.class);
    private static final String FAILED = "failed";

    private final EmailService emailService;

    public MailgunWebhookController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping(path = "/webhook")
    public void processBounce(@RequestBody List<WebhookDto> dtos) {
        LOGGER.info("webhook event ontvangen: {}", dtos);
        dtos.forEach(d -> {
            if (d.getEvent().equalsIgnoreCase(FAILED)) {
                emailService.verwerkBounce(d.getRecipient());
            }
        });
    }

}
