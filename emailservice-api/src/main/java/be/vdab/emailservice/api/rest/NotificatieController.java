package be.vdab.emailservice.api.rest;

import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificatieController {

    @GetMapping(path = "/geopend/{correlatieUuid}")
    public void emailGeopend(@PathVariable UUID correlatieUuid) {
    
    }
}
