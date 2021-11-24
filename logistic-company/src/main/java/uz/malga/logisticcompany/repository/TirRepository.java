package uz.malga.logisticcompany.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.malga.logisticcompany.entity.Tir;
import uz.malga.logisticcompany.entity.enums.CompanyName;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public interface TirRepository extends JpaRepository<Tir, UUID> {
    Page<Tir> findAllByActiveAndCreatedAtBetween(Boolean active, Timestamp dateFromString, Timestamp dateFromString1, Pageable pageableByCreatedAtDesc);

    List<Tir> findAllByTirNumberContainingIgnoringCase(String search);

    List<Tir> findAllByActiveAndCompanyId(boolean active, Long companyId);


}
