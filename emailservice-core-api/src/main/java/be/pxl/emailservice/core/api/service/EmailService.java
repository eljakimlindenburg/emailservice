package be.pxl.emailservice.core.api.service;

import be.pxl.emailservice.core.api.EmailDto;
import java.util.UUID;

public interface EmailService {

    void verstuurEmail(EmailDto dto);

    void verwerkBounce(String email);

    void emailGeopend(UUID uuid);
}
