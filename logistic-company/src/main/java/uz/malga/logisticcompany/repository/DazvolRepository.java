package uz.malga.logisticcompany.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.malga.logisticcompany.entity.Company;
import uz.malga.logisticcompany.entity.Dazvol;
import uz.malga.logisticcompany.entity.DazvolsName;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public interface DazvolRepository extends JpaRepository<Dazvol , UUID> {
    Page<Dazvol> findAllByActiveAndCreatedAtBetween(Boolean active, Timestamp dateFromString, Timestamp dateFromString1, Pageable pageableByCreatedAtDesc);

    List<Dazvol> findAllByDazvolNumberContainingIgnoringCase(String search);

    List<Dazvol>findAllByActiveAndCompanyIdAndDazvolNameId(boolean active, Long company_id, Long dazvolName_id);

    Page<Dazvol> findAllByActiveAndCompanyId(Pageable pageableByIdDesc, boolean active, Long company_id);

    List<Dazvol> findAllByCompanyAndDazvolName(Company company, DazvolsName dazvolName);
}
