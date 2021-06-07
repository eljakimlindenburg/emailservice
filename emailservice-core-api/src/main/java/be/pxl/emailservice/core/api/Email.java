package be.pxl.emailservice.core.api;

import be.pxl.emailservice.core.api.util.NestedBuilder;
import java.time.Instant;
import java.util.UUID;

public class Email {

    private UUID correlatieUuid;
    private Provider provider;
    private Status status;
    private Instant creatieTimestamp;
    private Instant laatsteUpdateTimestamp;
    private String geadresseerde;
    private String inhoud;

    public static Builder builder() {
        return new Builder();
    }

    private Email() {
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

    public Instant getCreatieTimestamp() {
        return creatieTimestamp;
    }

    public Instant getLaatsteUpdateTimestamp() {
        return laatsteUpdateTimestamp;
    }

    public String getGeadresseerde() {
        return geadresseerde;
    }

    public String getInhoud() {
        return inhoud;
    }

    public static class Builder extends NestedBuilder<Email> {

        private Builder() {
        }

        @Override
        protected Email createInstance() {
            return new Email();
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

        public Builder geadresseerde(String geadresseerde) {
            instance().geadresseerde = geadresseerde;
            return this;
        }

        public Builder inhoud(String inhoud) {
            instance().inhoud = inhoud;
            return this;
        }
    }
}
