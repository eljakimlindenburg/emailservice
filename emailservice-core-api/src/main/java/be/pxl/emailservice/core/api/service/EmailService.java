package be.pxl.emailservice.core.api.service;

import be.pxl.emailservice.core.api.Email;

public interface EmailService {

    void verstuurEmail(Email email);

    void updateEmail(Email email);
}
