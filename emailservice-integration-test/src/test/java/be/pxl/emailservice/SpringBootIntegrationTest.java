package be.pxl.emailservice;

import be.pxl.emailservice.stubs.Stub;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = RunApplication.class)
@ExtendWith(SpringExtension.class)
public abstract class SpringBootIntegrationTest {
    
    @Autowired
    private List<Stub> stubs;
    
    @BeforeEach
    public void resetStubs() {
        stubs.forEach(Stub::reset);
    }
    
}
