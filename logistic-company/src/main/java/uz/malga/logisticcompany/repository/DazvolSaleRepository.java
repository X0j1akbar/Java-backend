package uz.malga.logisticcompany.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.malga.logisticcompany.entity.DazvolSale;

import java.util.List;
import java.util.UUID;

public interface DazvolSaleRepository extends JpaRepository<DazvolSale, UUID> {
    List<DazvolSale> findAllByCarNumberContainingIgnoringCase(String search);

    Page<DazvolSale> findAllByCompanyId(Long company_id, Pageable pageable);

}
