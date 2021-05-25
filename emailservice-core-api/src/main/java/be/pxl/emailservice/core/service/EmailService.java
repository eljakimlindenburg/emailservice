package be.pxl.emailservice.core.service;

import be.pxl.emailservice.core.repository.Email;

public interface EmailService {
    
    void verstuurEmail(Email email);
}
