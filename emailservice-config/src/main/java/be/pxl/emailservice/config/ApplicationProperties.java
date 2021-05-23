package be.pxl.emailservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class ApplicationProperties {
    
    @Value("${spring.application.name}")
    private String applicationName;
    
    public String getApplicationName() {
        return applicationName;
    }
}
