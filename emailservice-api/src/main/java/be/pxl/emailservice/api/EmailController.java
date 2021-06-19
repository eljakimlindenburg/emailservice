package be.pxl.emailservice.api;

import static org.slf4j.LoggerFactory.getLogger;

import be.pxl.emailservice.api.dto.VerstuurEmailRequestDto;
import be.pxl.emailservice.core.api.Provider;
import be.pxl.emailservice.core.api.service.EmailService;
import java.util.UUID;
import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class EmailController {

    private static final Logger LOGGER = getLogger(EmailController.class);

    private final EmailValidator emailValidator;
    private final EmailService emailService;
    private final EmailMapper emailMapper;

    public EmailController(EmailService emailService, EmailMapper emailMapper, EmailValidator emailValidator) {
        this.emailService = emailService;
        this.emailMapper = emailMapper;
        this.emailValidator = emailValidator;
    }

    @GetMapping(value = "/{uuid}")
    public void notificeerEmailGeopend(@PathVariable UUID uuid) {
        emailService.updateEmailGeopend(uuid);
    }

    @PostMapping("/verstuur/stmp")
    public ResponseEntity<String> verstuurEmailViaEigenSmtp(VerstuurEmailRequestDto dto) {
        if (!emailValidator.isValid(dto.getGeadresseerdeEmail())) {
            return ResponseEntity.badRequest().build();
        }
        emailService.verstuurEmail(emailMapper.map(Provider.EIGEN_API, dto));
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/sendgrid/email/verstuur")
    public ResponseEntity<String> verstuurEmailViaSendGrid(@RequestBody VerstuurEmailRequestDto dto) {
        LOGGER.info("Request ontvangen");
        if (!emailValidator.isValid(dto.getGeadresseerdeEmail())) {
            LOGGER.info("Foutief emailadres");
            return ResponseEntity.badRequest().build();
        }
        emailService.verstuurEmail(emailMapper.map(Provider.SENDGRID, dto));
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/mailgun/email/verstuur")
    public ResponseEntity<String> verstuurEmailViaMailgun(VerstuurEmailRequestDto dto) {
        if (!emailValidator.isValid(dto.getGeadresseerdeEmail())) {
            LOGGER.info("Foutief emailadres");
            return ResponseEntity.badRequest().build();
        }
        emailService.verstuurEmail(emailMapper.map(Provider.MAILGUN, dto));
        return ResponseEntity.ok().build();
    }

}
