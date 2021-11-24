package uz.pdp.srmserver.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.srmserver.entitiy.Report;
import uz.pdp.srmserver.entitiy.Shop;
import uz.pdp.srmserver.entitiy.enums.ReportStatus;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

public interface ReportRepository extends JpaRepository<Report, UUID> {
    Optional<Report> findByShopAndStatus(Shop shop, ReportStatus status);
    Page<Report>findAllByApprovedAndCreatedAtBetween(boolean approved, Timestamp createdAt, Timestamp createdAt2, Pageable pageable);
}