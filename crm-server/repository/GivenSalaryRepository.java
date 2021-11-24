package uz.pdp.srmserver.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.srmserver.entitiy.GivenSalary;

import java.sql.Timestamp;
import java.util.UUID;

public interface GivenSalaryRepository extends JpaRepository<GivenSalary, UUID> {
    Page<GivenSalary> findAllByApprovedAndCreatedAtBetween(boolean approved, Timestamp createdAt, Timestamp createdAt2, Pageable pageable);
}