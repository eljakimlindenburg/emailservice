package be.pxl.emailservice.core.api.service;

import be.pxl.emailservice.core.api.EmailDto;
import java.util.UUID;

public interface EmailService {

    void verstuurEmail(EmailDto dto);

    void updateEmail(EmailDto dto);

    void updateEmailGeopend(UUID uuid);
}
