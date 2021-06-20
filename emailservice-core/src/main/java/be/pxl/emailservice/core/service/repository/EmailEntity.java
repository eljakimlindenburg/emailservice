package be.pxl.emailservice.core.service.repository;

import be.pxl.emailservice.core.api.Provider;
import be.pxl.emailservice.core.api.Status;
import be.pxl.emailservice.core.api.util.EqualsByStateObject;
import be.pxl.emailservice.core.api.util.NestedBuilder;
import java.time.Instant;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("squid:S2160")
@Entity
@Table(name = "EMAIL_QM")
public class EmailEntity extends EqualsByStateObject {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CORRELATIE_UUID")
    private UUID correlatieUuid;

    @Column(name = "PROVIDER")
    @Enumerated(EnumType.STRING)
    private Provider provider;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "CREATIE_TIMESTAMP")
    private Instant creatieTimestamp;

    @Column(name = "LAATSTE_UPDATE_TIMESTAMP")
    private Instant laatsteUpdateTimestamp;

    @Column(name = "AFZENDER")
    private String afzender;

    @Column(name = "GEADRESSEERDE_EMAIL")
    private String geadresseerdeEmail;

    @Column(name = "GEADRESSEERDE_NAAM")
    private String geadresseerdeNaam;

    @Column(name = "INHOUD")
    private String inhoud;

    @Column(name = "ONDERWERP")
    private String onderwerp;

    public static Builder builder() {
        return new Builder();
    }

    public Long getId() {
        return id;
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

    public String getAfzender() {
        return afzender;
    }

    public String getGeadresseerdeEmail() {
        return geadresseerdeEmail;
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

    public void setLaatsteUpdateTimestamp(Instant laatsteUpdateTimestamp) {
        this.laatsteUpdateTimestamp = laatsteUpdateTimestamp;
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

    public static class Builder extends NestedBuilder<EmailEntity> {

        private Builder() {
        }

        @Override
        protected EmailEntity createInstance() {
            return new EmailEntity();
        }

        public Builder id(Long id) {
            instance().id = id;
            return this;
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
