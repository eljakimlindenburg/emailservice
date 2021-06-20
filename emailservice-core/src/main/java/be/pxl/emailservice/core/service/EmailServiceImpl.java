package be.pxl.emailservice.core.service;

import static org.slf4j.LoggerFactory.getLogger;

import be.pxl.emailservice.core.api.EmailDto;
import be.pxl.emailservice.core.api.Provider;
import be.pxl.emailservice.core.api.Status;
import be.pxl.emailservice.core.api.service.EmailService;
import be.pxl.emailservice.core.api.service.MailgunService;
import be.pxl.emailservice.core.api.service.SendGridService;
import be.pxl.emailservice.core.api.service.SmtpService;
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
    private final MailgunService mailgunService;
    private final EmailEntityRepository emailRepository;

    public EmailServiceImpl(SmtpService smtpService, SendGridService sendGridService, MailgunService mailgunService, EmailEntityRepository emailRepository) {
        this.smtpService = smtpService;
        this.sendGridService = sendGridService;
        this.mailgunService = mailgunService;
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
            mailgunService.verstuurEmail(dto);
        }
        email.setStatus(dto.getStatus());
        LOGGER.info("status gewijzigd naar {}", email.getStatus());
        email.setLaatsteUpdateTimestamp(Instant.now());
        emailRepository.save(email);
    }

    @Override
    public void verwerkBounce(String emailadres) {
        var email = emailRepository.findFirstByGeadresseerdeEmailOrderByLaatsteUpdateTimestampDesc(emailadres);
        email.ifPresent(e -> {
            e.setStatus(Status.HARD_BOUNCE);
            LOGGER.info("hard bounce geregistreerd");
        });
    }

    @Override
    public void emailGeopend(UUID uuid) {
        var email = emailRepository.findByCorrelatieUuid(uuid);
        if (email.isPresent()) {
            email.get().setStatus(Status.GEOPEND);
            LOGGER.info("status gewijzigd naar {}", email.get().getStatus());
        } else {
            LOGGER.info("geen email gevonden voor correlatieUuid: {}", uuid);
        }
    }

    private EmailEntity findOrElseBuildNew(EmailDto dto) {
        return emailRepository.findByCorrelatieUuid(dto.getCorrelatieUuid()).orElse(EmailEntity.builder()
            .correlatieUuid(dto.getCorrelatieUuid())
            .status(Status.INITIEEL)
            .provider(dto.getProvider())
            .creatieTimestamp(dto.getCreatieTimestamp())
            .laatsteUpdateTimestamp(dto.getLaatsteUpdateTimestamp())
            .afzender(dto.getAfzender())
            .geadresseerdeEmail(dto.getGeadresseerdeEmail())
            .geadresseerdeNaam(dto.getGeadresseerdeNaam())
            .onderwerp(dto.getOnderwerp())
            .inhoud(dto.getInhoud())
            .build());
    }

}
