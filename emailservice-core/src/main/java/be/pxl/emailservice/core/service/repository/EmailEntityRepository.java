package be.pxl.emailservice.core.service.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailEntityRepository extends JpaRepository<EmailEntity, Long> {

    Optional<EmailEntity> findByCorrelatieUuid(UUID uuid);

    Optional<EmailEntity> findFirstByGeadresseerdeEmailOrderByLaatsteUpdateTimestampDesc(String emailadres);
}
