package be.pxl.emailservice.core.api.service;

import be.pxl.emailservice.core.api.Email;

public interface SendGridService {

    void verstuurEmail(Email email);
}
