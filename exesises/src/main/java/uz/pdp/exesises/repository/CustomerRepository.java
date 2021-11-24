package uz.pdp.exesises.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.exesises.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
