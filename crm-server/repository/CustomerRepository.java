package uz.pdp.srmserver.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.srmserver.entitiy.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Page<Customer>findByActive(boolean active, Pageable pageable);
}