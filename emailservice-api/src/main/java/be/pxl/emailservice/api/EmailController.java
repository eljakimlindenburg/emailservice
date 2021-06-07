package be.pxl.emailservice.api;

import be.pxl.emailservice.api.dto.VerstuurEmailRequest;
import be.pxl.emailservice.core.api.Provider;
import be.pxl.emailservice.core.api.service.EmailService;
import java.util.UUID;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/email")
public class EmailController {

    private final EmailValidator emailValidator;
    private final EmailService emailService;
    private final EmailMapper emailMapper;

    public EmailController(EmailService emailService, EmailMapper emailMapper, EmailValidator emailValidator) {
        this.emailService = emailService;
        this.emailMapper = emailMapper;
        this.emailValidator = emailValidator;
    }

    @GetMapping(value = "/{uuid}/geopend")
    public void notificeerEmailGeopend(@PathVariable UUID uuid) {
        throw new UnsupportedOperationException();
    }

    @PostMapping("/verstuur/stmp")
    public ResponseEntity<String> verstuurEmailViaEigenSmtp(VerstuurEmailRequest dto) {
        if (!emailValidator.isValid(dto.getGeadresseerde())) {
            return ResponseEntity.badRequest().build();
        }
        emailService.verstuurEmail(emailMapper.map(Provider.EIGEN_API, dto));
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/verstuur/sendgrid")
    public ResponseEntity<String> verstuurEmailViaSendGrid(VerstuurEmailRequest dto) {
        if (!emailValidator.isValid(dto.getGeadresseerde())) {
            return ResponseEntity.badRequest().build();
        }
        emailService.verstuurEmail(emailMapper.map(Provider.SENDGRID, dto));
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/verstuur/sparkpost")
    public ResponseEntity<String> verstuurEmailViaSparkPost(VerstuurEmailRequest dto) {
        if (!emailValidator.isValid(dto.getGeadresseerde())) {
            return ResponseEntity.badRequest().build();
        }
        emailService.verstuurEmail(emailMapper.map(Provider.SPARKPOST, dto));
        return ResponseEntity.ok().build();
    }

}
