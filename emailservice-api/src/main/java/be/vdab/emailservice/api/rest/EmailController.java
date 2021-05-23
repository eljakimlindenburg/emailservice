package be.vdab.emailservice.api.rest;

import be.vdab.emailservice.api.dto.VerstuurEmailDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/email/verstuur")
public class EmailController {
    
    @PostMapping("/stmp")
    public ResponseEntity<String> verstuurEmail(VerstuurEmailDto dto) {
        return ResponseEntity.ok().build();
    }
    
    @PostMapping(path = "/ringring")
    public ResponseEntity<String> verstuurEmailViaRingRing(VerstuurEmailDto dto) {
        return ResponseEntity.ok().build();
    }
    
    @PostMapping(path = "/sendgrid")
    public ResponseEntity<String> verstuurEmailViaSendGrid(VerstuurEmailDto dto) {
        return ResponseEntity.ok().build();
    }
}
