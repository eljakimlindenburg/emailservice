package be.pxl.emailservice.core.api;

import be.pxl.emailservice.core.api.util.NestedBuilder;
import java.time.Instant;
import java.util.UUID;

public class EmailDto {

    private UUID correlatieUuid;
    private Provider provider;
    private Status status;
    private Instant creatieTimestamp;
    private Instant laatsteUpdateTimestamp;
    private String afzender;
    private String geadresseerdeEmail;
    private String geadresseerdeNaam;
    private String inhoud;
    private String onderwerp;

    public static Builder builder() {
        return new Builder();
    }

    private EmailDto() {
    }

    public UUID getCorrelatieUuid() {
        return correlatieUuid;
    }

    public Provider getProvider() {
        return provider;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Instant getCreatieTimestamp() {
        return creatieTimestamp;
    }

    public Instant getLaatsteUpdateTimestamp() {
        return laatsteUpdateTimestamp;
    }

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

    public static class Builder extends NestedBuilder<EmailDto> {

        private Builder() {
        }

        @Override
        protected EmailDto createInstance() {
            return new EmailDto();
        }

        public Builder correlatieUuid(UUID correlatieUuid) {
            instance().correlatieUuid = correlatieUuid;
            return this;
        }

        public Builder provider(Provider provider) {
            instance().provider = provider;
            return this;
        }

        public Builder status(Status status) {
            instance().status = status;
            return this;
        }

        public Builder creatieTimestamp(Instant creatieTimestamp) {
            instance().creatieTimestamp = creatieTimestamp;
            return this;
        }

        public Builder laatsteUpdateTimestamp(Instant laatsteUpdateTimestamp) {
            instance().laatsteUpdateTimestamp = laatsteUpdateTimestamp;
            return this;
        }

        public Builder afzender(String afzender) {
            instance().afzender = afzender;
            return this;
        }

        public Builder geadresseerdeEmail(String geadresseerdeEmail) {
            instance().geadresseerdeEmail = geadresseerdeEmail;
            return this;
        }

        public Builder geadresseerdeNaam(String geadresseerdeNaam) {
            instance().geadresseerdeNaam = geadresseerdeNaam;
            return this;
        }

        public Builder inhoud(String inhoud) {
            instance().inhoud = inhoud;
            return this;
        }

        public Builder onderwerp(String onderwerp) {
            instance().onderwerp = onderwerp;
            return this;
        }
    }
}
