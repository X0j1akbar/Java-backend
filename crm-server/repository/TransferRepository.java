package uz.pdp.srmserver.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.srmserver.entitiy.Transfer;

import java.sql.Timestamp;
import java.util.UUID;

public interface TransferRepository extends JpaRepository<Transfer, UUID> {
    Page<Transfer>findAllByApprovedAndCreatedAtBetween(boolean approved, Timestamp createdAt, Timestamp createdAt2, Pageable pageable);
}