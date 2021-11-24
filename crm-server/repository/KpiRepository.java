package uz.pdp.srmserver.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.srmserver.entitiy.Kpi;

import java.util.List;

public interface KpiRepository extends JpaRepository<Kpi, Integer> {
    Page<Kpi> findAllByActive(Boolean active, Pageable simplePageable);

    List<Kpi> findAllByNameContainingIgnoringCase(String search);

}