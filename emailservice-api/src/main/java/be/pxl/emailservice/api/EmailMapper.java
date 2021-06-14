package be.pxl.emailservice.api;

import be.pxl.emailservice.api.dto.VerstuurEmailRequestDto;
import be.pxl.emailservice.core.api.EmailDto;
import be.pxl.emailservice.core.api.Provider;
import be.pxl.emailservice.core.api.Status;
import java.time.Instant;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class EmailMapper {

    public EmailDto map(Provider provider, VerstuurEmailRequestDto dto) {
        return EmailDto.builder()
            .correlatieUuid(UUID.randomUUID())
            .status(Status.INITIEEL)
            .provider(provider)
            .creatieTimestamp(Instant.now())
            .laatsteUpdateTimestamp(Instant.now())
            .afzender(dto.getAfzender())
            .geadresseerdeEmail(dto.getGeadresseerdeEmail())
            .geadresseerdeNaam(dto.getGeadresseerdeNaam())
            .inhoud(dto.getInhoud())
            .onderwerp(dto.getOnderwerp())
            .build();
    }

}
