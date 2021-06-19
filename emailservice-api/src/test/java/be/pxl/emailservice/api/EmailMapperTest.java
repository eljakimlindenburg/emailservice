package be.pxl.emailservice.api;

import static be.pxl.emailservice.api.dto.VerstuurEmailDtoRequestTestBuilder.aVerstuurEmailRequest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import be.pxl.emailservice.api.dto.VerstuurEmailRequestDto;
import be.pxl.emailservice.core.api.EmailDto;
import be.pxl.emailservice.core.api.Provider;
import be.pxl.emailservice.core.api.Status;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EmailMapperTest {

    @InjectMocks
    private EmailMapper emailMapper;

    @Test
    void givenProviderSendGrid_whenMap_thenReturnCorrectEmail() {
        VerstuurEmailRequestDto dto = aVerstuurEmailRequest().build();
        EmailDto emailDto = emailMapper.map(Provider.SENDGRID, dto);

        assertNotNull(emailDto.getCorrelatieUuid());
        assertThat(emailDto.getProvider()).isEqualTo(Provider.SENDGRID);
        assertThat(emailDto.getStatus()).isEqualTo(Status.INITIEEL);
        assertThat(emailDto.getGeadresseerdeEmail()).isEqualTo(dto.getGeadresseerdeEmail());
        assertThat(emailDto.getGeadresseerdeNaam()).isEqualTo(dto.getGeadresseerdeNaam());
        assertThat(emailDto.getInhoud()).isEqualTo(dto.getInhoud());
        assertThat(emailDto.getCreatieTimestamp()).isBetween(Instant.now().minus(1, ChronoUnit.SECONDS), Instant.now());
        assertThat(emailDto.getLaatsteUpdateTimestamp()).isBetween(Instant.now().minus(1, ChronoUnit.SECONDS), Instant.now());
    }

    @Test
    void givenProviderSparkPost_whenMap_thenReturnCorrectEmail() {
        VerstuurEmailRequestDto dto = aVerstuurEmailRequest().build();
        EmailDto emailDto = emailMapper.map(Provider.MAILGUN, dto);

        assertNotNull(emailDto.getCorrelatieUuid());
        assertThat(emailDto.getProvider()).isEqualTo(Provider.MAILGUN);
        assertThat(emailDto.getStatus()).isEqualTo(Status.INITIEEL);
        assertThat(emailDto.getGeadresseerdeEmail()).isEqualTo(dto.getGeadresseerdeEmail());
        assertThat(emailDto.getGeadresseerdeNaam()).isEqualTo(dto.getGeadresseerdeNaam());
        assertThat(emailDto.getInhoud()).isEqualTo(dto.getInhoud());
        assertThat(emailDto.getCreatieTimestamp()).isBetween(Instant.now().minus(1, ChronoUnit.SECONDS), Instant.now());
        assertThat(emailDto.getLaatsteUpdateTimestamp()).isBetween(Instant.now().minus(1, ChronoUnit.SECONDS), Instant.now());
    }

    @Test
    void givenProviderEigenApi_whenMap_thenReturnCorrectEmail() {
        VerstuurEmailRequestDto dto = aVerstuurEmailRequest().build();
        EmailDto emailDto = emailMapper.map(Provider.EIGEN_API, dto);

        assertNotNull(emailDto.getCorrelatieUuid());
        assertThat(emailDto.getProvider()).isEqualTo(Provider.EIGEN_API);
        assertThat(emailDto.getStatus()).isEqualTo(Status.INITIEEL);
        assertThat(emailDto.getGeadresseerdeEmail()).isEqualTo(dto.getGeadresseerdeEmail());
        assertThat(emailDto.getGeadresseerdeNaam()).isEqualTo(dto.getGeadresseerdeNaam());
        assertThat(emailDto.getInhoud()).isEqualTo(dto.getInhoud());
        assertThat(emailDto.getCreatieTimestamp()).isBetween(Instant.now().minus(1, ChronoUnit.SECONDS), Instant.now());
        assertThat(emailDto.getLaatsteUpdateTimestamp()).isBetween(Instant.now().minus(1, ChronoUnit.SECONDS), Instant.now());
    }
}
