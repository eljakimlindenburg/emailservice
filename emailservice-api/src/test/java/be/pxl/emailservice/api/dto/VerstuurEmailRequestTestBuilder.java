package be.pxl.emailservice.api.dto;

import be.pxl.emailservice.test.infrastructure.util.Whitebox;

public class VerstuurEmailRequestTestBuilder {

    private String geadresseerde = "eljakim.l@gmail.com";
    private String inhoud = "testInhoud";

    public static VerstuurEmailRequestTestBuilder aVerstuurEmailRequest() {
        return new VerstuurEmailRequestTestBuilder();
    }

    private VerstuurEmailRequestTestBuilder() {
    }

    public VerstuurEmailRequestTestBuilder geadresseerde(String geadresseerde) {
        this.geadresseerde = geadresseerde;
        return this;
    }

    public VerstuurEmailRequestTestBuilder inhoud(String inhoud) {
        this.inhoud = inhoud;
        return this;
    }

    public VerstuurEmailRequest build() {
        VerstuurEmailRequest dto = Whitebox.createInstance(VerstuurEmailRequest.class);
        Whitebox.setInternalState(dto, "geadresseerde", geadresseerde);
        Whitebox.setInternalState(dto, "inhoud", inhoud);
        return dto;
    }
}
