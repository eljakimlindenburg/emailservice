package be.pxl.emailservice.core.api.service;

import be.pxl.emailservice.core.api.Email;

public interface SmtpService {

    void verstuurEmail(Email email);
}
