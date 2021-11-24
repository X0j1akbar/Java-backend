package uz.pdp.srmserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.srmserver.entitiy.ProductWithAmount;

import java.util.UUID;

public interface ProductWithAmountRepository extends JpaRepository<ProductWithAmount, UUID> {
}