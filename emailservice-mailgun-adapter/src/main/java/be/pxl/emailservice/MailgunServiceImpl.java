package be.pxl.emailservice;

import be.pxl.emailservice.core.api.EmailDto;
import be.pxl.emailservice.core.api.service.MailgunService;
import org.springframework.stereotype.Component;

@Component
public class MailgunServiceImpl implements MailgunService {

    @Override
    public void verstuurEmail(EmailDto emailDto) {

    }
}
