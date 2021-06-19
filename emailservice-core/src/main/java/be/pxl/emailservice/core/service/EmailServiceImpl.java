package be.pxl.emailservice.core.service;

import static org.slf4j.LoggerFactory.getLogger;

import be.pxl.emailservice.core.api.EmailDto;
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
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl implements EmailService {

    private static final Logger LOGGER = getLogger(EmailServiceImpl.class);

    private final SmtpService smtpService;
    private final SendGridService sendGridService;
    private final SparkPostService sparkPostService;
    private final EmailEntityRepository emailRepository;

    public EmailServiceImpl(SmtpService smtpService, SendGridService sendGridService, SparkPostService sparkPostService, EmailEntityRepository emailRepository) {
        this.smtpService = smtpService;
        this.sendGridService = sendGridService;
        this.sparkPostService = sparkPostService;
        this.emailRepository = emailRepository;
    }

    @Override
    public void verstuurEmail(EmailDto dto) {
        var email = findOrElseBuildNew(dto);
        if (Provider.EIGEN_API.equals(dto.getProvider())) {
            smtpService.verstuurEmail(dto);
        }
        if (Provider.SENDGRID.equals(dto.getProvider())) {
            dto = sendGridService.verstuurEmail(dto);
        }
        if (Provider.MAILGUN.equals(dto.getProvider())) {
            sparkPostService.verstuurEmail(dto);
        }
        email.setStatus(dto.getStatus());
        LOGGER.info("status gewijzigd naar {}", email.getStatus());
        email.setLaatsteUpdateTimestamp(Instant.now());
        emailRepository.save(email);
    }

    @Override
    public void updateEmail(EmailDto emailDto) {

    }

    @Override
    public void updateEmailGeopend(UUID uuid) {
        var email = emailRepository.findByCorrelatieUuid(uuid);
        email.ifPresent(e -> {
            e.setStatus(Status.GEOPEND);
            LOGGER.info("status gewijzigd naar {}", e.getStatus());
        });
    }

    private EmailEntity findOrElseBuildNew(EmailDto dto) {
        return emailRepository.findByCorrelatieUuid(dto.getCorrelatieUuid()).orElse(EmailEntity.builder()
            .correlatieUuid(UUID.randomUUID())
            .status(Status.INITIEEL)
            .provider(dto.getProvider())
            .creatieTimestamp(Instant.now())
            .laatsteUpdateTimestamp(Instant.now())
            .afzender(dto.getAfzender())
            .geadresseerdeEmail(dto.getGeadresseerdeEmail())
            .geadresseerdeNaam(dto.getGeadresseerdeNaam())
            .onderwerp(dto.getOnderwerp())
            .inhoud(dto.getInhoud())
            .build());
    }

}
