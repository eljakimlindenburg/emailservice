package be.vdab.emailservice.api.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import be.vdab.emailservice.test.infrastructure.util.UnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.http.ResponseEntity;

class EmailControllerTest implements UnitTest {
    
    @InjectMocks
    private EmailController emailController;
    
    @Test
    void givenValidDto_whenVerstuurEmailSmtp_thenVerstuurEmailUsingSmtp_andReturnOk() {
        ResponseEntity<String> expected = ResponseEntity.ok().build();
        
        ResponseEntity<String> actual = emailController.verstuurEmail();
        
        assertEquals(expected, actual);
    }
    
    @Test
    void givenValidDto_whenVerstuurEmailRingRing_thenVerstuurEmailUsingRingRing_andReturnOk() {
        ResponseEntity<String> expected = ResponseEntity.ok().build();
        
        ResponseEntity<String> actual = emailController.verstuurEmailViaRingRing();
        
        assertEquals(expected, actual);
    }
    
    @Test
    void givenValidDto_whenVerstuurEmailSendGrid_thenVerstuurEmailUsingSendGrid_andReturnOk() {
        ResponseEntity<String> expected = ResponseEntity.ok().build();
        
        ResponseEntity<String> actual = emailController.verstuurEmailViaSendGrid();
        
        assertEquals(expected, actual);
    }
}
