package be.pxl.emailservice.api;

import static be.pxl.emailservice.api.dto.VerstuurEmailDtoRequestTestBuilder.aVerstuurEmailRequest;
import static be.pxl.emailservice.core.api.EmailTestBuilder.anEmail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import be.pxl.emailservice.api.dto.VerstuurEmailRequestDto;
import be.pxl.emailservice.core.api.EmailDto;
import be.pxl.emailservice.core.api.Provider;
import be.pxl.emailservice.core.api.service.EmailService;
import org.apache.commons.validator.routines.EmailValidator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class EmailControllerTest {

    @Mock
    private EmailValidator emailValidator;

    @Mock
    private EmailService emailService;

    @Mock
    private EmailMapper emailMapper;

    @InjectMocks
    private EmailController emailController;

    @AfterEach
    void after() {
        verifyNoMoreInteractions(emailValidator);
        verifyNoMoreInteractions(emailService);
        verifyNoMoreInteractions(emailMapper);
    }

    @Test
    void givenValidDto_whenVerstuurEmailSmtp_thenVerstuurEmailUsingSmtp_andReturnOk() {
        ResponseEntity<String> expected = ResponseEntity.ok().build();
        VerstuurEmailRequestDto dto = aVerstuurEmailRequest().build();
        when(emailValidator.isValid(dto.getGeadresseerdeEmail())).thenReturn(true);
        EmailDto emailDto = anEmail().provider(Provider.EIGEN_API).build();
        when(emailMapper.map(Provider.EIGEN_API, dto)).thenReturn(emailDto);

        ResponseEntity<String> actual = emailController.verstuurEmailViaEigenSmtp(dto);

        assertEquals(expected, actual);
        verify(emailValidator).isValid(dto.getGeadresseerdeEmail());
        verify(emailMapper).map(Provider.EIGEN_API, dto);
        verify(emailService).verstuurEmail(emailDto);
    }

    @Test
    void givenValidDto_whenVerstuurEmailSendGrid_thenVerstuurEmailUsingSendGrid_andReturnOk() {
        ResponseEntity<String> expected = ResponseEntity.ok().build();
        VerstuurEmailRequestDto dto = aVerstuurEmailRequest().build();
        when(emailValidator.isValid(dto.getGeadresseerdeEmail())).thenReturn(true);
        EmailDto emailDto = anEmail().provider(Provider.SENDGRID).build();
        when(emailMapper.map(Provider.SENDGRID, dto)).thenReturn(emailDto);

        ResponseEntity<String> actual = emailController.verstuurEmailViaSendGrid(dto);

        assertEquals(expected, actual);
        verify(emailValidator).isValid(dto.getGeadresseerdeEmail());
        verify(emailMapper).map(Provider.SENDGRID, dto);
        verify(emailService).verstuurEmail(emailDto);
    }

    @Test
    void givenValidDto_whenVerstuurEmailSparkPost_thenVerstuurEmailUsingSparkPost_andReturnOk() {
        ResponseEntity<String> expected = ResponseEntity.ok().build();
        VerstuurEmailRequestDto dto = aVerstuurEmailRequest().build();
        when(emailValidator.isValid(dto.getGeadresseerdeEmail())).thenReturn(true);
        EmailDto emailDto = anEmail().provider(Provider.MAILGUN).build();
        when(emailMapper.map(Provider.MAILGUN, dto)).thenReturn(emailDto);

        ResponseEntity<String> actual = emailController.verstuurEmailViaMailgun(dto);

        assertEquals(expected, actual);
        verify(emailValidator).isValid(dto.getGeadresseerdeEmail());
        verify(emailMapper).map(Provider.MAILGUN, dto);
        verify(emailService).verstuurEmail(emailDto);
    }

    @Test
    void givenInvalidEmailAddress_whenVerstuurEmailSendGrid_thenReturnBadRequest() {
        ResponseEntity<String> expected = ResponseEntity.badRequest().build();
        VerstuurEmailRequestDto dto = aVerstuurEmailRequest().build();
        when(emailValidator.isValid(dto.getGeadresseerdeEmail())).thenReturn(false);

        ResponseEntity<String> actual = emailController.verstuurEmailViaSendGrid(dto);

        assertEquals(expected, actual);
        verify(emailValidator).isValid(dto.getGeadresseerdeEmail());
    }

    @Test
    void givenInvalidEmailAddress_whenVerstuurEmailSparkPost_thenReturnBadRequest() {
        ResponseEntity<String> expected = ResponseEntity.badRequest().build();
        VerstuurEmailRequestDto dto = aVerstuurEmailRequest().build();
        when(emailValidator.isValid(dto.getGeadresseerdeEmail())).thenReturn(false);

        ResponseEntity<String> actual = emailController.verstuurEmailViaMailgun(dto);

        assertEquals(expected, actual);
        verify(emailValidator).isValid(dto.getGeadresseerdeEmail());
    }

    @Test
    void givenInvalidEmailAddress_whenVerstuurEmailSmtp_thenReturnBadRequest() {
        ResponseEntity<String> expected = ResponseEntity.badRequest().build();
        VerstuurEmailRequestDto dto = aVerstuurEmailRequest().build();
        when(emailValidator.isValid(dto.getGeadresseerdeEmail())).thenReturn(false);

        ResponseEntity<String> actual = emailController.verstuurEmailViaEigenSmtp(dto);

        assertEquals(expected, actual);
        verify(emailValidator).isValid(dto.getGeadresseerdeEmail());
    }
}
