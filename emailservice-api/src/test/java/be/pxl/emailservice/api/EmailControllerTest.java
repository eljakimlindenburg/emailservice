package be.pxl.emailservice.api;

import static be.pxl.emailservice.api.dto.VerstuurEmailRequestTestBuilder.aVerstuurEmailRequest;
import static be.pxl.emailservice.core.api.EmailTestBuilder.anEmail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import be.pxl.emailservice.api.EmailController;
import be.pxl.emailservice.api.EmailMapper;
import be.pxl.emailservice.api.dto.VerstuurEmailRequest;
import be.pxl.emailservice.core.api.Email;
import be.pxl.emailservice.core.api.Provider;
import be.pxl.emailservice.core.api.service.EmailService;
import be.pxl.emailservice.test.infrastructure.util.UnitTest;
import org.apache.commons.validator.routines.EmailValidator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

class EmailControllerTest implements UnitTest {

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
        VerstuurEmailRequest dto = aVerstuurEmailRequest().build();
        when(emailValidator.isValid(dto.getGeadresseerde())).thenReturn(true);
        Email email = anEmail().provider(Provider.EIGEN_API).build();
        when(emailMapper.map(Provider.EIGEN_API, dto)).thenReturn(email);

        ResponseEntity<String> actual = emailController.verstuurEmailViaEigenSmtp(dto);

        assertEquals(expected, actual);
        verify(emailValidator).isValid(dto.getGeadresseerde());
        verify(emailMapper).map(Provider.EIGEN_API, dto);
        verify(emailService).verstuurEmail(email);
    }

    @Test
    void givenValidDto_whenVerstuurEmailSendGrid_thenVerstuurEmailUsingSendGrid_andReturnOk() {
        ResponseEntity<String> expected = ResponseEntity.ok().build();
        VerstuurEmailRequest dto = aVerstuurEmailRequest().build();
        when(emailValidator.isValid(dto.getGeadresseerde())).thenReturn(true);
        Email email = anEmail().provider(Provider.SENDGRID).build();
        when(emailMapper.map(Provider.SENDGRID, dto)).thenReturn(email);

        ResponseEntity<String> actual = emailController.verstuurEmailViaSendGrid(dto);

        assertEquals(expected, actual);
        verify(emailValidator).isValid(dto.getGeadresseerde());
        verify(emailMapper).map(Provider.SENDGRID, dto);
        verify(emailService).verstuurEmail(email);
    }

    @Test
    void givenValidDto_whenVerstuurEmailSparkPost_thenVerstuurEmailUsingSparkPost_andReturnOk() {
        ResponseEntity<String> expected = ResponseEntity.ok().build();
        VerstuurEmailRequest dto = aVerstuurEmailRequest().build();
        when(emailValidator.isValid(dto.getGeadresseerde())).thenReturn(true);
        Email email = anEmail().provider(Provider.SPARKPOST).build();
        when(emailMapper.map(Provider.SPARKPOST, dto)).thenReturn(email);

        ResponseEntity<String> actual = emailController.verstuurEmailViaSparkPost(dto);

        assertEquals(expected, actual);
        verify(emailValidator).isValid(dto.getGeadresseerde());
        verify(emailMapper).map(Provider.SPARKPOST, dto);
        verify(emailService).verstuurEmail(email);
    }

    @Test
    void givenInvalidEmailAddress_whenVerstuurEmailSendGrid_thenReturnBadRequest() {
        ResponseEntity<String> expected = ResponseEntity.badRequest().build();
        VerstuurEmailRequest dto = aVerstuurEmailRequest().build();
        when(emailValidator.isValid(dto.getGeadresseerde())).thenReturn(false);

        ResponseEntity<String> actual = emailController.verstuurEmailViaSendGrid(dto);

        assertEquals(expected, actual);
        verify(emailValidator).isValid(dto.getGeadresseerde());
    }

    @Test
    void givenInvalidEmailAddress_whenVerstuurEmailSparkPost_thenReturnBadRequest() {
        ResponseEntity<String> expected = ResponseEntity.badRequest().build();
        VerstuurEmailRequest dto = aVerstuurEmailRequest().build();
        when(emailValidator.isValid(dto.getGeadresseerde())).thenReturn(false);

        ResponseEntity<String> actual = emailController.verstuurEmailViaSparkPost(dto);

        assertEquals(expected, actual);
        verify(emailValidator).isValid(dto.getGeadresseerde());
    }

    @Test
    void givenInvalidEmailAddress_whenVerstuurEmailSmtp_thenReturnBadRequest() {
        ResponseEntity<String> expected = ResponseEntity.badRequest().build();
        VerstuurEmailRequest dto = aVerstuurEmailRequest().build();
        when(emailValidator.isValid(dto.getGeadresseerde())).thenReturn(false);

        ResponseEntity<String> actual = emailController.verstuurEmailViaEigenSmtp(dto);

        assertEquals(expected, actual);
        verify(emailValidator).isValid(dto.getGeadresseerde());
    }
}
