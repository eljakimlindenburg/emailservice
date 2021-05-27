package be.pxl.emailservice.stubs;

import static org.springframework.web.bind.annotation.RequestMethod.HEAD;

import be.pxl.emailservice.sparkpost.dto.SparkPostRequestDto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stub/sparkpost")
public class SparkPostStub implements Stub {
    
    private final Map<String, List<SparkPostRequestDto>> receivedEmailRequests = new HashMap<>();
    private HttpStatus status = HttpStatus.OK;
    
    @RequestMapping(method = HEAD, path = "/info")
    public ResponseEntity<String> info() {
        return ResponseEntity.status(this.status).build();
    }
    
    @Override
    public void reset() {
        this.status = HttpStatus.OK;
        this.receivedEmailRequests.clear();
    }
    
    public void setUnavailable() {
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }
    
    public void setStatus(HttpStatus status) {
        this.status = status;
    }
    
    public List<SparkPostRequestDto> getReceivedEmailRequests(String emailAdres) {
        return receivedEmailRequests.getOrDefault(emailAdres, new ArrayList<>());
    }
    
}