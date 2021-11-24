package uz.pdp.srmserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.srmserver.entitiy.Sale;

import java.util.UUID;

public interface SaleRepository extends JpaRepository<Sale, UUID> {
    Sale getById(UUID id);
}