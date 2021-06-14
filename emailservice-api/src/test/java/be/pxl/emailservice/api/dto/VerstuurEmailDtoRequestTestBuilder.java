package be.pxl.emailservice.api.dto;

import be.pxl.emailservice.test.infrastructure.util.Whitebox;

public class VerstuurEmailDtoRequestTestBuilder {

    private String afzender = "eljakim.lindenburg@student.pxl.be";
    private String geadresseerdeEmail = "eljakim.l@gmail.com";
    private String geadresseerdeNaam = "Eljakim Lindenburg";
    private String inhoud = "testInhoud";
    private String onderwerp = "testonderwerp";

    public static VerstuurEmailDtoRequestTestBuilder aVerstuurEmailRequest() {
        return new VerstuurEmailDtoRequestTestBuilder();
    }

    private VerstuurEmailDtoRequestTestBuilder() {
    }

    public VerstuurEmailDtoRequestTestBuilder afzender(String afzender) {
        this.afzender = afzender;
        return this;
    }

    public VerstuurEmailDtoRequestTestBuilder geadresseerdeEmail(String geadresseerdeEmail) {
        this.geadresseerdeEmail = geadresseerdeEmail;
        return this;
    }

    public VerstuurEmailDtoRequestTestBuilder geadresseerdeNaam(String geadresseerdeNaam) {
        this.geadresseerdeNaam = geadresseerdeNaam;
        return this;
    }

    public VerstuurEmailDtoRequestTestBuilder inhoud(String inhoud) {
        this.inhoud = inhoud;
        return this;
    }

    public VerstuurEmailDtoRequestTestBuilder onderwerp(String onderwerp) {
        this.onderwerp = onderwerp;
        return this;
    }

    public VerstuurEmailRequestDto build() {
        VerstuurEmailRequestDto dto = Whitebox.createInstance(VerstuurEmailRequestDto.class);
        Whitebox.setInternalState(dto, "afzender", afzender);
        Whitebox.setInternalState(dto, "geadresseerdeEmail", geadresseerdeEmail);
        Whitebox.setInternalState(dto, "geadresseerdeNaam", geadresseerdeNaam);
        Whitebox.setInternalState(dto, "inhoud", inhoud);
        Whitebox.setInternalState(dto, "onderwerp", onderwerp);
        return dto;
    }
}
