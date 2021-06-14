package be.pxl.emailservice.sendgrid;

import be.pxl.emailservice.core.api.EmailDto;
import be.pxl.emailservice.core.api.service.SendGridService;
import org.springframework.stereotype.Component;

@Component
public class SendGridServiceImpl implements SendGridService {

    private final SendGridGateway gateway;
    private final SendGridEmailRequestMapper mapper;

    public SendGridServiceImpl(SendGridGateway gateway, SendGridEmailRequestMapper mapper) {
        this.gateway = gateway;
        this.mapper = mapper;
    }

    @Override
    public EmailDto verstuurEmail(EmailDto dto) {
        return gateway.sendEmail(dto, mapper.mapToRequest(dto));
    }

}
