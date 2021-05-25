package be.pxl.emailservice.api.rest;

import be.pxl.emailservice.api.dto.VerstuurEmailDto;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/email")
public class EmailController {
    
    @GetMapping(value = "/{uuid}/geopend")
    public void emailGeopend(@PathVariable UUID uuid) {
    
    }
    
    @PostMapping("/verstuur/stmp")
    public ResponseEntity<String> verstuurEmail(VerstuurEmailDto dto) {
        return ResponseEntity.ok().build();
    }
    
    @PostMapping(path = "/verstuur/sendgrid")
    public ResponseEntity<String> verstuurEmailViaSendGrid(VerstuurEmailDto dto) {
        return ResponseEntity.ok().build();
    }
    
    @PostMapping(path = "/verstuur/sparkpost")
    public ResponseEntity<String> verstuurEmailViaSparkPost(VerstuurEmailDto dto) {
        return ResponseEntity.ok().build();
    }
    
    
}
