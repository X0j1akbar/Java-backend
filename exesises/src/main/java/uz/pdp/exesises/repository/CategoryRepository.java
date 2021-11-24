package uz.pdp.exesises.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.exesises.entity.Category;
import uz.pdp.exesises.entity.Product;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
