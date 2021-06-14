package be.pxl.emailservice.core.api.service;

import be.pxl.emailservice.core.api.EmailDto;

public interface SendGridService {

    EmailDto verstuurEmail(EmailDto emailDto);
}
