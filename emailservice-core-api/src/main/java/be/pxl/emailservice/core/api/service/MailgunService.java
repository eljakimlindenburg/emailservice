package be.pxl.emailservice.core.api.service;

import be.pxl.emailservice.core.api.EmailDto;

public interface MailgunService {

    void verstuurEmail(EmailDto emailDto);
}
