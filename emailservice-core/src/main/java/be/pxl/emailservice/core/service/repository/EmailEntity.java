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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@SuppressWarnings("squid:S2160")
@Entity
@Table(name = "EMAIL_QM")
public class EmailEntity extends EqualsByStateObject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMAIL_QM_SEQ")
    @SequenceGenerator(name = "EMAIL_QM_SEQ", sequenceName = "EMAIL_QM_SEQ", allocationSize = 1)
    @Column
    private Long id;

    @Column(name = "CORRELATIE_UUID")
    private UUID correlatieUuid;

    @Column(name = "PROVIDER")
    @Enumerated(EnumType.STRING)
    private Provider provider;

    @Column(name = "STATUS")
    private Status status;

    @Column(name = "CREATIE_TIMESTAMP")
    private Instant creatieTimestamp;

    @Column(name = "LAATSTE_UPDATE_TIMESTAMP")
    private Instant laatsteUpdateTimestamp;

    @Column(name = "GEADRESSEERDE")
    private String geadresseerde;

    @Column(name = "INHOUD")
    private String inhoud;

    public static Builder builder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getCorrelatieUuid() {
        return correlatieUuid;
    }

    public void setCorrelatieUuid(UUID correlatieUuid) {
        this.correlatieUuid = correlatieUuid;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
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

    public void setCreatieTimestamp(Instant creatieTimestamp) {
        this.creatieTimestamp = creatieTimestamp;
    }

    public Instant getLaatsteUpdateTimestamp() {
        return laatsteUpdateTimestamp;
    }

    public void setLaatsteUpdateTimestamp(Instant laatsteUpdateTimestamp) {
        this.laatsteUpdateTimestamp = laatsteUpdateTimestamp;
    }

    public String getGeadresseerde() {
        return geadresseerde;
    }

    public void setGeadresseerde(String geadresseerde) {
        this.geadresseerde = geadresseerde;
    }

    public String getInhoud() {
        return inhoud;
    }

    public void setInhoud(String inhoud) {
        this.inhoud = inhoud;
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
