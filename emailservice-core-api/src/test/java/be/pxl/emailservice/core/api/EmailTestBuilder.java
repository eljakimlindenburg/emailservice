package be.pxl.emailservice.core.api;

import java.time.Instant;
import java.util.UUID;

public class EmailTestBuilder {

    private UUID correlatieUuid = UUID.randomUUID();
    private Provider provider = Provider.EIGEN_API;
    private Status status = Status.INITIEEL;
    private Instant creatieTimestamp = Instant.now();
    private Instant laatsteUpdateTimestamp = Instant.now();
    private String geadresseerde;
    private String inhoud;

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

    public EmailTestBuilder geadresseerde(String geadresseerde) {
        this.geadresseerde = geadresseerde;
        return this;
    }

    public EmailTestBuilder inhoud(String inhoud) {
        this.inhoud = inhoud;
        return this;
    }

    public Email build() {
        return Email.builder()
            .correlatieUuid(correlatieUuid)
            .provider(provider)
            .status(status)
            .creatieTimestamp(creatieTimestamp)
            .laatsteUpdateTimestamp(laatsteUpdateTimestamp)
            .geadresseerde(geadresseerde)
            .inhoud(inhoud)
            .build();
    }
}
