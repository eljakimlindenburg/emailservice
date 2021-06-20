package be.pxl.emailservice.sendgrid;

import be.pxl.emailservice.core.api.service.EmailService;
import be.pxl.emailservice.sendgrid.events.BounceDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/sendgrid")
public class SendGridWebhookController {

    private final EmailService emailService;

    public SendGridWebhookController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping(path = "/bounce")
    public void processBounce(@RequestBody BounceDto dto) {
        if (dto.getEvent().equalsIgnoreCase("bounce")) {
            emailService.verwerkBounce(dto.getEmail());
        }
    }
}
