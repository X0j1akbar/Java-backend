package uz.pdp.srmserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.srmserver.entitiy.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category getById(Integer id);
    List<Category> findAllByNameContainingIgnoringCase(String name);
}