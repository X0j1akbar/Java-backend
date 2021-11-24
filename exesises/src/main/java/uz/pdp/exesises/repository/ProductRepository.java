package uz.pdp.exesises.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.exesises.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {


}
