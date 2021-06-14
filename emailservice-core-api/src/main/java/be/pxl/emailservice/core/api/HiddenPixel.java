package be.pxl.emailservice.core.api;

import java.util.UUID;

public class HiddenPixel {

    private final String pixel;

    public static HiddenPixel from(UUID correlatieUuid, String url) {
        return new HiddenPixel(correlatieUuid, url);
    }

    private HiddenPixel(UUID correlatieUuid, String url) {
        this.pixel = createHiddenPixel(correlatieUuid, url);
    }

    private String createHiddenPixel(UUID correlatieUuid, String url) {
        return String.format("<img src=\"%s/%s\" style=\"display:none\" />", url, correlatieUuid);
    }

    public String getPixel() {
        return pixel;
    }
}
