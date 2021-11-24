package uz.pdp.srmserver.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.srmserver.entitiy.Salary;

import java.sql.Timestamp;
import java.util.UUID;

public interface SalaryRepository extends JpaRepository<Salary, UUID> {
    Page<Salary>findAllByCreatedAtBetween(Timestamp createdAt, Timestamp createdAt2, Pageable pageable);
}