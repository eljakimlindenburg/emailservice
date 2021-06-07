package be.pxl.emailservice.api;

import be.pxl.emailservice.api.dto.VerstuurEmailRequest;
import be.pxl.emailservice.core.api.Email;
import be.pxl.emailservice.core.api.Provider;
import be.pxl.emailservice.core.api.Status;
import java.time.Instant;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class EmailMapper {

    public Email map(Provider provider, VerstuurEmailRequest dto) {
        return Email.builder()
            .correlatieUuid(UUID.randomUUID())
            .status(Status.INITIEEL)
            .provider(provider)
            .creatieTimestamp(Instant.now())
            .laatsteUpdateTimestamp(Instant.now())
            .geadresseerde(dto.getGeadresseerde())
            .inhoud(dto.getInhoud())
            .build();
    }
}
