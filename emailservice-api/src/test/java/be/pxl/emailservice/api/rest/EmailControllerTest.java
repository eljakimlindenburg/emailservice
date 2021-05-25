package be.pxl.emailservice.api.rest;

import static be.pxl.emailservice.api.dto.VerstuurEmailDtoTestBuilder.aVerstuurEmailDto;
import static org.junit.jupiter.api.Assertions.assertEquals;

import be.pxl.emailservice.api.dto.VerstuurEmailDto;
import be.pxl.emailservice.test.infrastructure.util.UnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.http.ResponseEntity;

class EmailControllerTest implements UnitTest {
    
    @InjectMocks
    private EmailController emailController;
    
    @Test
    void givenValidDto_whenVerstuurEmailSmtp_thenVerstuurEmailUsingSmtp_andReturnOk() {
        ResponseEntity<String> expected = ResponseEntity.ok().build();
        VerstuurEmailDto dto = aVerstuurEmailDto().build();
        
        ResponseEntity<String> actual = emailController.verstuurEmail(dto);
        
        assertEquals(expected, actual);
    }
    
    @Test
    void givenValidDto_whenVerstuurEmailRingRing_thenVerstuurEmailUsingRingRing_andReturnOk() {
        ResponseEntity<String> expected = ResponseEntity.ok().build();
        VerstuurEmailDto dto = aVerstuurEmailDto().build();
        
        ResponseEntity<String> actual = emailController.verstuurEmailViaSendGrid(dto);
        
        assertEquals(expected, actual);
    }
    
    @Test
    void givenValidDto_whenVerstuurEmailSparkPost_thenVerstuurEmailUsingSparkPost_andReturnOk() {
        ResponseEntity<String> expected = ResponseEntity.ok().build();
        VerstuurEmailDto dto = aVerstuurEmailDto().build();
        
        ResponseEntity<String> actual = emailController.verstuurEmailViaSparkPost(dto);
        
        assertEquals(expected, actual);
    }
}
