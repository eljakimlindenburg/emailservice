package be.pxl.emailservice.core.repository;

import static javax.persistence.EnumType.STRING;

import be.pxl.emailservice.core.util.EqualsByStateObject;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@SuppressWarnings("squid:S2160")
@Entity
@Table(name = "EMAIL_QM")
public class Email extends EqualsByStateObject {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMAIL_QM_SEQ")
    @SequenceGenerator(name = "EMAIL_QM_SEQ", sequenceName = "EMAIL_QM_SEQ", allocationSize = 1)
    @Column
    private Long id;
    
    @Column(name = "CORRELATIE_UUID")
    private UUID correlatieUuid;
    
    @Column(name = "PROVIDER")
    @Enumerated(STRING)
    private Provider provider;
    
    public Long getId() {
        return id;
    }
    
    public UUID getCorrelatieUuid() {
        return correlatieUuid;
    }
    
    public Provider getProvider() {
        return provider;
    }
}
