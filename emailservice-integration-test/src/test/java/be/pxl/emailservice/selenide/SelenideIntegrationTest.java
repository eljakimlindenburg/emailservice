package be.pxl.emailservice.selenide;

import static org.slf4j.LoggerFactory.getLogger;

import be.pxl.emailservice.SpringBootIntegrationTest;
import org.slf4j.Logger;

public abstract class SelenideIntegrationTest extends SpringBootIntegrationTest {
    
    private static final Logger LOGGER = getLogger(SelenideIntegrationTest.class);
    
    /* @Autowired
    private EmailRepository emailRepository;
    
    @Autowired
    private Stub stub;
    
    @Autowired
    private WebDriverFactory webDriverFactory;
    
    @BeforeEach
    public void clearTables() {
        emailRepository.deleteAll();
    }
    
    @BeforeEach
    public void setupWebdriver() {
        WebDriverRunner.setWebDriver(webDriverFactory.getWebDriver());
        Configuration.reportsFolder = "target/screenshots/";
    }
     */
}
