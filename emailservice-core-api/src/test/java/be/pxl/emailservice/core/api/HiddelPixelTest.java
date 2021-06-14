package be.pxl.emailservice.core.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import be.pxl.emailservice.test.infrastructure.util.UnitTest;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class HiddelPixelTest implements UnitTest {

    @Test
    void givenParameters_whenFrom_thenReturnCorrectPixel() {
        var uuid = UUID.randomUUID();
        var url = "www.test.be";
        var expected = String.format("<img src=\"%s/%s\" style=\"display:none\" />", url, uuid);

        var actual = HiddenPixel.from(uuid, url);

        assertEquals(expected, actual.getPixel());
    }
}
