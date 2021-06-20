package be.pxl.emailservice.smtp;

import be.pxl.emailservice.core.api.EmailDto;
import be.pxl.emailservice.core.api.service.SmtpService;
import org.springframework.stereotype.Service;

@Service
public class SmtpServiceImpl implements SmtpService {

    @Override
    public void verstuurEmail(EmailDto emailDto) {
        throw new UnsupportedOperationException();
    }
}
