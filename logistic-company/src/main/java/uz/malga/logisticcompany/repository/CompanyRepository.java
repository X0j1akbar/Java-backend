package uz.malga.logisticcompany.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.malga.logisticcompany.entity.Company;

public interface CompanyRepository extends JpaRepository<Company,Long> {
}
