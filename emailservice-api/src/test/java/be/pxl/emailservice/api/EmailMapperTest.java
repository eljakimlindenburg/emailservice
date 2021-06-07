package be.pxl.emailservice.api;

import static be.pxl.emailservice.api.dto.VerstuurEmailRequestTestBuilder.aVerstuurEmailRequest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import be.pxl.emailservice.api.dto.VerstuurEmailRequest;
import be.pxl.emailservice.core.api.Email;
import be.pxl.emailservice.core.api.Provider;
import be.pxl.emailservice.core.api.Status;
import be.pxl.emailservice.test.infrastructure.util.UnitTest;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

class EmailMapperTest implements UnitTest {

    @InjectMocks
    private EmailMapper emailMapper;

    @Test
    void givenProviderSendGrid_whenMap_thenReturnCorrectEmail() {
        VerstuurEmailRequest dto = aVerstuurEmailRequest().build();
        Email email = emailMapper.map(Provider.SENDGRID, dto);

        assertNotNull(email.getCorrelatieUuid());
        assertThat(email.getProvider()).isEqualTo(Provider.SENDGRID);
        assertThat(email.getStatus()).isEqualTo(Status.INITIEEL);
        assertThat(email.getGeadresseerde()).isEqualTo(dto.getGeadresseerde());
        assertThat(email.getInhoud()).isEqualTo(dto.getInhoud());
        assertThat(email.getCreatieTimestamp()).isBetween(Instant.now().minus(1, ChronoUnit.SECONDS), Instant.now());
        assertThat(email.getLaatsteUpdateTimestamp()).isBetween(Instant.now().minus(1, ChronoUnit.SECONDS), Instant.now());
    }

    @Test
    void givenProviderSparkPost_whenMap_thenReturnCorrectEmail() {
        VerstuurEmailRequest dto = aVerstuurEmailRequest().build();
        Email email = emailMapper.map(Provider.SPARKPOST, dto);

        assertNotNull(email.getCorrelatieUuid());
        assertThat(email.getProvider()).isEqualTo(Provider.SPARKPOST);
        assertThat(email.getStatus()).isEqualTo(Status.INITIEEL);
        assertThat(email.getGeadresseerde()).isEqualTo(dto.getGeadresseerde());
        assertThat(email.getInhoud()).isEqualTo(dto.getInhoud());
        assertThat(email.getCreatieTimestamp()).isBetween(Instant.now().minus(1, ChronoUnit.SECONDS), Instant.now());
        assertThat(email.getLaatsteUpdateTimestamp()).isBetween(Instant.now().minus(1, ChronoUnit.SECONDS), Instant.now());
    }

    @Test
    void givenProviderEigenApi_whenMap_thenReturnCorrectEmail() {
        VerstuurEmailRequest dto = aVerstuurEmailRequest().build();
        Email email = emailMapper.map(Provider.EIGEN_API, dto);

        assertNotNull(email.getCorrelatieUuid());
        assertThat(email.getProvider()).isEqualTo(Provider.EIGEN_API);
        assertThat(email.getStatus()).isEqualTo(Status.INITIEEL);
        assertThat(email.getGeadresseerde()).isEqualTo(dto.getGeadresseerde());
        assertThat(email.getInhoud()).isEqualTo(dto.getInhoud());
        assertThat(email.getCreatieTimestamp()).isBetween(Instant.now().minus(1, ChronoUnit.SECONDS), Instant.now());
        assertThat(email.getLaatsteUpdateTimestamp()).isBetween(Instant.now().minus(1, ChronoUnit.SECONDS), Instant.now());
    }
}
