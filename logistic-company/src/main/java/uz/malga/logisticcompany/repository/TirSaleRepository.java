package uz.malga.logisticcompany.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.malga.logisticcompany.entity.TirSale;

import java.util.List;
import java.util.UUID;

public interface TirSaleRepository extends JpaRepository<TirSale, UUID> {
    List<TirSale> findAllByCarNumberContainingIgnoringCase(String search);

    Page<TirSale> findAllByCompanyId(Long companyId, Pageable pageableByIdDesc);

}
