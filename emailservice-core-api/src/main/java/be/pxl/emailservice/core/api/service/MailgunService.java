package be.pxl.emailservice.core.api.service;

import be.pxl.emailservice.core.api.EmailDto;

public interface MailgunService {

    EmailDto verstuurEmail(EmailDto emailDto);
}
