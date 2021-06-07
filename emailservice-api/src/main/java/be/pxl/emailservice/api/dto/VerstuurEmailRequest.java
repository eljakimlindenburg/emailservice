package be.pxl.emailservice.api.dto;

import be.pxl.emailservice.core.api.util.EqualsByStateObject;

public class VerstuurEmailRequest extends EqualsByStateObject {

    private String geadresseerde;
    private String inhoud;

    public String getGeadresseerde() {
        return geadresseerde;
    }

    public String getInhoud() {
        return inhoud;
    }
}
