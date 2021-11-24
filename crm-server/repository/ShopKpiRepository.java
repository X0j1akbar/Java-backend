package uz.pdp.srmserver.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.srmserver.entitiy.Salary;
import uz.pdp.srmserver.entitiy.ShopKpi;

import java.sql.Timestamp;
import java.util.UUID;

public interface ShopKpiRepository extends JpaRepository<ShopKpi, UUID> {
    Page<ShopKpi> findAllByCreatedAtBetween(Timestamp createdAt, Timestamp createdAt2, Pageable pageable);
}