package be.pxl.emailservice.api.rest.feedback;

import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/email")
public class EmailNotificatieController {

    @GetMapping(path = "/{correlatieUuid}/geopend")
    public void emailGeopend(@PathVariable UUID correlatieUuid) {
    
    }
}
