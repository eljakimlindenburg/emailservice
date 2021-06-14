package be.pxl.emailservice.core.api;

import java.time.Instant;
import java.util.UUID;

public class EmailTestBuilder {

    private UUID correlatieUuid = UUID.randomUUID();
    private Provider provider = Provider.EIGEN_API;
    private Status status = Status.INITIEEL;
    private Instant creatieTimestamp = Instant.now();
    private Instant laatsteUpdateTimestamp = Instant.now();
    private String afzender = "afzender";
    private String geadresseerdeEmail = "test@google.be";
    private String geadresseerdeNaam = "test mij";
    private String inhoud = "testinhoud";
    private String onderwerp = "testonderwerp";

    public static EmailTestBuilder anEmail() {
        return new EmailTestBuilder();
    }

    private EmailTestBuilder() {
    }

    public EmailTestBuilder correlatieUuid(UUID correlatieUuid) {
        this.correlatieUuid = correlatieUuid;
        return this;
    }

    public EmailTestBuilder provider(Provider provider) {
        this.provider = provider;
        return this;
    }

    public EmailTestBuilder status(Status status) {
        this.status = status;
        return this;
    }

    public EmailTestBuilder creatieTimestamp(Instant creatieTimestamp) {
        this.creatieTimestamp = creatieTimestamp;
        return this;
    }

    public EmailTestBuilder laatsteUpdateTimestamp(Instant laatsteUpdateTimestamp) {
        this.laatsteUpdateTimestamp = laatsteUpdateTimestamp;
        return this;
    }

    public EmailTestBuilder afzender(String afzender) {
        this.afzender = afzender;
        return this;
    }

    public EmailTestBuilder geadresseerdeEmail(String geadresseerdeEmail) {
        this.geadresseerdeEmail = geadresseerdeEmail;
        return this;
    }

    public EmailTestBuilder geadresseerdeNaam(String geadresseerdeNaam) {
        this.geadresseerdeNaam = geadresseerdeNaam;
        return this;
    }

    public EmailTestBuilder inhoud(String inhoud) {
        this.inhoud = inhoud;
        return this;
    }

    public EmailTestBuilder onderwerp(String onderwerp) {
        this.onderwerp = onderwerp;
        return this;
    }

    public EmailDto build() {
        return EmailDto.builder()
            .correlatieUuid(correlatieUuid)
            .provider(provider)
            .status(status)
            .creatieTimestamp(creatieTimestamp)
            .laatsteUpdateTimestamp(laatsteUpdateTimestamp)
            .afzender(afzender)
            .geadresseerdeEmail(geadresseerdeEmail)
            .geadresseerdeNaam(geadresseerdeNaam)
            .inhoud(inhoud)
            .onderwerp(onderwerp)
            .build();
    }
}
