package uz.pdp.srmserver.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.srmserver.entitiy.Product;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Page<Product>findAllByActiveAndCreatedAtBetween(boolean active, Timestamp createdAt, Timestamp createdAt2, Pageable pageable);

    List<Product> findAllByNameContainingIgnoringCase(String search);

}