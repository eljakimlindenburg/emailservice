package be.vdab.emailservice.api.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/email")
public class EmailController {
    
    @PostMapping(path = "/verstuur")
    public void verstuurEmail() {
    
    }
    
    @PostMapping(path = "/verstuur/aangetekend")
    public void verstuurAangetekendeEmailViaDigiConnect() {
    
    }
    
    @PostMapping(path = "/verstuur/ringring")
    public void verstuurEmailViaRingRing() {
    
    }
    
    @PostMapping(path = "/verstuur/ringring")
    public void verstuurEmailViaSendGrid() {
    
    }
}
