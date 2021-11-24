package uz.pdp.srmserver.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.srmserver.entitiy.Bonus;

import java.sql.Timestamp;
import java.util.UUID;

public interface BonusRepository extends JpaRepository<Bonus, UUID> {
    Page<Bonus> findAllByApprovedAndCreatedAtBetween(boolean approved, Timestamp createdAt, Timestamp createdAt2, Pageable pageable);

    Bonus getById(UUID id);
}