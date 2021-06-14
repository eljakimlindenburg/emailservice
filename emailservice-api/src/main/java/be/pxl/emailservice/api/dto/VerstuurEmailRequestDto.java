package be.pxl.emailservice.api.dto;

import be.pxl.emailservice.core.api.util.EqualsByStateObject;

public class VerstuurEmailRequestDto extends EqualsByStateObject {

    private String afzender;
    private String geadresseerdeEmail;
    private String geadresseerdeNaam;
    private String inhoud;
    private String onderwerp;

    public String getAfzender() {
        return afzender;
    }

    public String getGeadresseerdeEmail() {
        return geadresseerdeEmail;
    }

    public String getGeadresseerdeNaam() {
        return geadresseerdeNaam;
    }

    public String getInhoud() {
        return inhoud;
    }

    public String getOnderwerp() {
        return onderwerp;
    }
}
