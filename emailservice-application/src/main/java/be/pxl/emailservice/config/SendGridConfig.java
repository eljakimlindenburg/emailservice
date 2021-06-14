package be.pxl.emailservice.config;

import com.sendgrid.SendGrid;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SendGridConfig {

    private final ApplicationProperties properties;

    public SendGridConfig(ApplicationProperties properties) {
        this.properties = properties;
    }

    @Bean
    public SendGrid sendGrid() {
        return new SendGrid(properties.getSendGridApiKey());
    }
}
