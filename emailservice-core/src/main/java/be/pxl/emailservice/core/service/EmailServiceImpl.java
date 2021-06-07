package be.pxl.emailservice.core.service;

import be.pxl.emailservice.core.api.Email;
import be.pxl.emailservice.core.api.Provider;
import be.pxl.emailservice.core.api.Status;
import be.pxl.emailservice.core.api.service.EmailService;
import be.pxl.emailservice.core.api.service.SendGridService;
import be.pxl.emailservice.core.api.service.SmtpService;
import be.pxl.emailservice.core.api.service.SparkPostService;
import be.pxl.emailservice.core.service.repository.EmailEntity;
import be.pxl.emailservice.core.service.repository.EmailEntityRepository;
import java.time.Instant;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private final SmtpService smtpService;
    private final SendGridService sendGridService;
    private final SparkPostService sparkPostService;
    private final EmailEntityRepository emailRepository;

    public EmailServiceImpl(SmtpService smtpService,
        SendGridService sendGridService,
        SparkPostService sparkPostService,
        EmailEntityRepository emailRepository) {
        this.smtpService = smtpService;
        this.sendGridService = sendGridService;
        this.sparkPostService = sparkPostService;
        this.emailRepository = emailRepository;
    }

    @Override
    public void verstuurEmail(Email email) {
        EmailEntity emailEntity = findOrElse(email);
        if (Provider.EIGEN_API.equals(email.getProvider())) {
            smtpService.verstuurEmail(email);
        }
        if (Provider.SENDGRID.equals(email.getProvider())) {
            sendGridService.verstuurEmail(email);
        }
        if (Provider.SPARKPOST.equals(email.getProvider())) {
            sparkPostService.verstuurEmail(email);
        }
        emailEntity.setStatus(Status.VERZONDEN);
        emailEntity.setLaatsteUpdateTimestamp(Instant.now());
        emailRepository.save(emailEntity);
    }

    @Override
    public void updateEmail(Email email) {

    }

    private EmailEntity findOrElse(Email email) {
        return emailRepository.findByCorrelatieUuid(email.getCorrelatieUuid()).orElse(EmailEntity.builder()
            .correlatieUuid(UUID.randomUUID())
            .status(Status.INITIEEL)
            .provider(email.getProvider())
            .creatieTimestamp(Instant.now())
            .laatsteUpdateTimestamp(Instant.now())
            .build());
    }

}
