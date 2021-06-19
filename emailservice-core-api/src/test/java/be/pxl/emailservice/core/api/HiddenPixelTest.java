package be.pxl.emailservice.core.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class HiddenPixelTest {

    @Test
    void givenParameters_whenFrom_thenReturnCorrectPixel() {
        var uuid = UUID.randomUUID();
        var url = "www.test.be";
        var expected = String.format("<img src=\"%s/%s\" style=\"display:none\" />", url, uuid);

        var actual = HiddenPixel.from(uuid, url);

        assertEquals(expected, actual.getPixel());
    }
}
